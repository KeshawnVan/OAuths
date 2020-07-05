package com.keshawn.oauth.server.manager;

import com.keshawn.oauth.server.model.App;
import com.keshawn.oauth.server.model.GetTokenResponse;
import com.keshawn.oauth.server.repository.AppRepository;
import com.keshawn.oauth.server.util.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class AppManager {

    @Autowired
    private AppRepository appRepository;

    public Tuple<Boolean, String> checkApp(String appCode) {
        if (StringUtils.isEmpty(appCode)) {
            return new Tuple<>(Boolean.FALSE, "app code cannot be null");
        }

        App app = appRepository.findByCode(appCode);

        if (app == null) {
            return new Tuple<>(Boolean.FALSE, "app code invalid");
        }

        return new Tuple<>(Boolean.TRUE, null);
    }

    public Tuple<Boolean, String> checkAppWithSecret(String appCode, String secret) {
        if (StringUtils.isEmpty(appCode)) {
            return new Tuple<>(Boolean.FALSE, "app code cannot be null");
        }

        if (StringUtils.isEmpty(secret)) {
            return new Tuple<>(Boolean.FALSE, "secret cannot be null");
        }

        App app = appRepository.findByCode(appCode);

        if (app == null) {
            return new Tuple<>(Boolean.FALSE, "app code invalid");
        }

        if (!Objects.equals(app.getSecret(), secret)) {
            return new Tuple<>(Boolean.FALSE, "secret incorrect");
        }

        return new Tuple<>(Boolean.TRUE, null);
    }
}
