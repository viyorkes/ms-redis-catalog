package com.redis.catalog.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;


@Configuration
public class RedissonConfig {

    private RedissonClient redissonClient;

    public RedissonClient getClient(){
        if(Objects.isNull(this.redissonClient)){
            Config config = new Config();
            config.useSingleServer()
                    .setAddress("redis://127.0.0.1:6379");
            redissonClient = Redisson.create(config);
        }
        return redissonClient;
    }

    public RedissonReactiveClient getReactiveClient(){
        return getClient().reactive();
    }
}