package com.keshawn.oauth.server.repository;

public interface CacheRepository {

    boolean set(String key, String value, Long timeoutSeconds);

    String get(String key);

}
