package com.keshawn.oauth.server.manager;


import com.keshawn.oauth.server.model.App;
import com.keshawn.oauth.server.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VerifyCodeManager {

    @Autowired
    private CacheRepository cacheRepository;

    public String generateVerifyCode(App app, String openId) {
        String verifyCode = app.getCode() + UUID.randomUUID().toString();
        saveVerifyCode(verifyCode, openId);
        return verifyCode;
    }

    private void saveVerifyCode(String verifyCode, String openId) {
        cacheRepository.set(verifyCode, openId, 60L);
    }

    public boolean checkVerifyCode(String appCode, String verifyCode) {
        return verifyCode != null && verifyCode.startsWith(appCode);
    }

    public String getOpenIdByVerifyCode(String verifyCode) {
        return cacheRepository.get(verifyCode);
    }
}
