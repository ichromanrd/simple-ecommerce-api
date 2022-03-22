package com.blockchainspace.ecommerce.service;

import com.blockchainspace.ecommerce.dto.AuthProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Log4j2
public class AuthService {

    private final String KEY_PREFIX = "auth_props:";

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ObjectMapper objectMapper;

    public void setAuthProperties(int userId, AuthProperties authProperties) throws JsonProcessingException {
        String key = getKey(userId);
        String value = objectMapper.writeValueAsString(authProperties);
        cacheService.put(key, value);
    }

    public AuthProperties getAuthPropertiesFromCache(int userId) throws JsonProcessingException {
        String key = getKey(userId);

        log.info("Retrieving auth cache with key: " + key);

        String value = cacheService.get(key);
        if (Objects.isNull(value)) {
            throw new RuntimeException("No auth data found");
        }

        return objectMapper.readValue(value, AuthProperties.class);
    }

    private String getKey(int userId) {
        return KEY_PREFIX + userId;
    }

}
