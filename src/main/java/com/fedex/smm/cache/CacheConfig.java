package com.fedex.smm.cache;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//@Configuration
public class CacheConfig /* extends CachingConfigurerSupport */ {

//	@Value("${spring.redis.host}")
//    private String REDIS_HOSTNAME;
//    @Value("${spring.redis.port}")
//    private int REDIS_PORT;
//    @Value("${spring.redis.timeout}")
//    private int TIME_OUT;
//	
//    private Map<String, Long> cacheExpirations = new HashMap<>();
//    
//    private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(timeoutInSeconds));
//    }
//
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(REDIS_HOSTNAME);
//        redisStandaloneConfiguration.setPort(REDIS_PORT);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
//
//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//        return createCacheConfiguration(TIME_OUT);
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
//
//        for (Entry<String, Long> cacheNameAndTimeout : cacheExpirations.entrySet()) {
//            cacheConfigurations.put(cacheNameAndTimeout.getKey(), createCacheConfiguration(cacheNameAndTimeout.getValue()));
//        }
//
//        return RedisCacheManager
//                .builder(redisConnectionFactory)
//                .cacheDefaults()
//                .withInitialCacheConfigurations(cacheConfigurations).build();
//    }
}