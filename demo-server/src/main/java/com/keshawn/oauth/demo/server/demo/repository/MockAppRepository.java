package com.keshawn.oauth.demo.server.demo.repository;

import com.keshawn.oauth.server.model.App;
import com.keshawn.oauth.server.repository.AppRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MockAppRepository implements AppRepository {

    @Override
    public App findByCode(String code) {
        if (Objects.equals("mock", code)) {
            App app = new App();
            app.setCode("mock");
            app.setId("1");
            app.setName("mock app");
            app.setSecret("123456");
            app.setRedirectUrl("http://localhost:9090/callback");
            return app;
        }
        return null;
    }
}
