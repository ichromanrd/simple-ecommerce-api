package com.blockchainspace.ecommerce.config.jwt;

import com.blockchainspace.ecommerce.dto.request.AuthRequest;
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
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;

@Log4j2
public class JwtUsernamePasswordAuthFilter extends OncePerRequestFilter {

    private final String POST = HttpMethod.POST.toString();

    private final JwtProperties properties;
    private final JdbcRealm realm;
    private final ObjectMapper objectMapper;

    public JwtUsernamePasswordAuthFilter(JwtProperties properties, Realm realm) {
        this.properties = properties;
        this.realm = (JdbcRealm) realm;
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
        } catch (JsonProcessingException | AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Instant now = Instant.now();
        AuthorizationInfo info = realm.getAuthorizationInfo(subject);
        String token = Jwts.builder().setSubject((String) subject.getPrincipal()).claim("roles", info.getRoles())
                .claim("permissions", info.getStringPermissions())
                .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(properties.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, properties.getSecret().getBytes()).compact();
        response.addHeader(properties.getHeader(), properties.getPrefix() + " " + token);
    }
}
