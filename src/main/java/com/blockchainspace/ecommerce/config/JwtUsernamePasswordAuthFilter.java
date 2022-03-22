package com.blockchainspace.ecommerce.config;

import com.blockchainspace.ecommerce.dto.AuthProperties;
import com.blockchainspace.ecommerce.dto.request.AuthRequest;
import com.blockchainspace.ecommerce.dto.response.AuthResponse;
import com.blockchainspace.ecommerce.dto.response.UserIdResponse;
import com.blockchainspace.ecommerce.service.AuthService;
import com.blockchainspace.ecommerce.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Log4j2
public class JwtUsernamePasswordAuthFilter extends OncePerRequestFilter {

    private final String POST = HttpMethod.POST.toString();

    private final JwtProperties properties;
    private final JdbcRealm realm;
    private final UserService userService;
    private final AuthService authService;
    private final ObjectMapper objectMapper;

    public JwtUsernamePasswordAuthFilter(JwtProperties properties, Realm realm, UserService userService,
            AuthService authService) {
        this.properties = properties;
        this.realm = (JdbcRealm) realm;
        this.userService = userService;
        this.authService = authService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected boolean isEnabled(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        return httpReq.getMethod().equalsIgnoreCase(POST) && httpReq.getServletPath().equals(properties.getUrl());
    }

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Subject subject = SecurityUtils.getSubject();
        try {
            AuthRequest authRequest = objectMapper.readValue(servletRequest.getInputStream(), AuthRequest.class);
            subject.login(new UsernamePasswordToken(authRequest.getUsername(), authRequest.getPassword()));
            cacheAuthProperties(subject, authRequest.getUsername());
        } catch (JsonProcessingException | AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Instant now = Instant.now();
        AuthorizationInfo info = realm.getAuthorizationInfo(subject);
        Date expirationDate = Date.from(now.plusSeconds(properties.getExpiration()));
        String token = Jwts.builder().setSubject((String) subject.getPrincipal()).claim("roles", info.getRoles())
                .claim("permissions", info.getStringPermissions())
                .setIssuedAt(Date.from(now)).setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, properties.getSecret().getBytes()).compact();

        response.addHeader(properties.getHeader(), properties.getPrefix() + " " + token);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        AuthResponse authResponse = AuthResponse.builder().accessToken(token)
                .expiresIn(simpleDateFormat.format(expirationDate))
                .build();
        writeResponse(response, authResponse);
    }

    private void cacheAuthProperties(Subject subject, String username) throws Exception {
        UserIdResponse user = userService.getUserByUsername(username);
        AuthProperties authProperties = AuthProperties.builder().userId(user.getId())
                .firstName(user.getFirstName()).lastName(user.getLastName()).build();
        authService.setAuthProperties(user.getId(), authProperties);
    }

    private void writeResponse(HttpServletResponse response, AuthResponse authResponse)
            throws IOException {
        String payload = objectMapper.writeValueAsString(authResponse);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(payload.getBytes(StandardCharsets.UTF_8));
    }
}
