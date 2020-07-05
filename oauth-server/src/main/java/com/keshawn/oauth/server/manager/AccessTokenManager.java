package com.keshawn.oauth.server.manager;

import com.keshawn.oauth.server.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccessTokenManager {

    @Autowired
    private CacheRepository cacheRepository;

    public String generateAccessToken(String openId) {
        String accessToken = UUID.randomUUID().toString();
        cacheRepository.set(openId, accessToken, 10 * 60L);
        return accessToken;
    }

}
