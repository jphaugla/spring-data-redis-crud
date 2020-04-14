package com.jphaugla.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class RedisConfig {
    @Value("${redis_host:localhost}")
    private String redis_host;
    @Value("${redis_port:6379}")
    private int redis_port;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redis_host, redis_port);
        return new JedisConnectionFactory(config);
    }
}
