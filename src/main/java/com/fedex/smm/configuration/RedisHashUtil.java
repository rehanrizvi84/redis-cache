package com.fedex.smm.configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisHashUtil<T> {

	private HashOperations<String, Object, T> hashOperations;

	RedisTemplate<String, T> redisTemplate;

	@Value("${redis.cache.expiry.ttl.value}")
	private long ttl;

	@Value("${redis.cache.expiry.ttl.unit}")
	private TimeUnit unit;

	private boolean isFullyLoaded;

	@Autowired
	public RedisHashUtil(RedisTemplate<String, T> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = redisTemplate.opsForHash();
	}

	public void add(String key, Object hashKey, T value) {
		hashOperations.put(key, hashKey, value);
		redisTemplate.expire(key, ttl, unit);
	}

	public void remove(String key, Object... hashKeys) {
		hashOperations.delete(key, hashKeys);
	}

	public void addAll(String key, Map<? extends Object, ? extends T> hashKey) {
		hashOperations.putAll(key, hashKey);
		redisTemplate.expire(key, ttl, unit);
	}

	public T get(String key, Object hashKey) {
		return hashOperations.get(key, hashKey);
	}

	public Map<Object, T> getAll(String key) {
		return hashOperations.entries(key);
	}

	public List<T> getValues(String key) {
		return hashOperations.values(key);

	}

	public Set<Object> getKeys(String key) {
		return hashOperations.keys(key);
	}

	public boolean isExists(String key, Object hashKey) {
		Boolean hasKey = hashOperations.hasKey(key, hashKey);
		return hasKey.booleanValue();
	}

	public boolean isEmpty(String key) {
		return hashOperations.entries(key).isEmpty();
	}

	public boolean isFullyLoaded() {
		return isFullyLoaded;
	}

	public void setAllLoaded(boolean isFullyLoaded) {
		this.isFullyLoaded = isFullyLoaded;
	}

}
