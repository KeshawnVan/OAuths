package com.keshawn.oauth.demo.server.demo.repository;

import com.keshawn.oauth.server.repository.CacheRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class MapCacheRepository implements CacheRepository {

    private static final ConcurrentMap<String, String> CACHE = new ConcurrentHashMap<>();

    @Override
    public boolean set(String key, String value, Long timeoutSeconds) {
        CACHE.put(key, value);
        return true;
    }

    @Override
    public String get(String key) {
        return CACHE.get(key);
    }
}
