package com.keshawn.oauth.server.repository;

import com.keshawn.oauth.server.model.App;

public interface AppRepository {

    App findByCode(String code);

}
