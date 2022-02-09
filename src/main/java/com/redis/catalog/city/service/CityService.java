package com.redis.catalog.city.service;

import com.redis.catalog.city.client.CityClient;
import com.redis.catalog.city.model.dto.City;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



@Service
public class    CityService {

    @Autowired
    private CityClient cityClient;

    private RMapReactive<String, City> cityMap;

    public CityService(RedissonReactiveClient client) {
        this.cityMap = client.getMap("city", new TypedJsonJacksonCodec(String.class, City.class));
    }

    public Mono<City> getCity(final String zipCode){

        return this.cityMap.get(zipCode)
                        .switchIfEmpty(this.cityClient.getCity(zipCode)
                                .flatMap(c-> this.cityMap.fastPut(zipCode,c).thenReturn(c)));



    }



}