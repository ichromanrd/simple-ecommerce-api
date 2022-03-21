package com.blockchainspace.ecommerce.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Authenticate requests with header 'Authorization: Bearer jwt-token'.
 *
 * @author shuaicj 2018/08/03
 */
@Slf4j
public class JwtTokenAuthFilter extends OncePerRequestFilter {

    private final JwtProperties properties;

    public JwtTokenAuthFilter(JwtProperties properties) {
        this.properties = properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;
        String token = req.getHeader(properties.getHeader());
        if (token != null && token.startsWith(properties.getPrefix() + " ")) {
            token = token.replace(properties.getPrefix() + " ", "");
            Claims claims = null;
            // verify token
            try {
                claims = Jwts.parser().setSigningKey(properties.getSecret().getBytes())
                        .parseClaimsJws(token).getBody();
            } catch (Exception e) {
                log.warn("jwt token verification failed", e);
            }
            // login automatically
            if (claims != null) {
                String principal = claims.getSubject();
                List<String> roles = claims.get("roles", List.class);
                List<String> permissions = claims.get("permissions", List.class);
                try {
                    SecurityUtils.getSubject().login(new JwtToken(principal, token, roles, permissions));
                } catch (AuthenticationException e) {
                    log.error("authentication failed", e);
                    rsp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        }
        chain.doFilter(req, rsp);
    }
}
