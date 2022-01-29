package com.redis.catalog;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucketReactive;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01KeyValueTest extends BaseTest {

    @Test
    public void  KeyValueAccessTest(){
        RBucketReactive<String> bucket = this.client.getBucket("user:1:name");
        Mono<Void> set= bucket.set("victor");
        Mono<Void> get = bucket.get()
                .doOnNext(System.out::println)
                .then();

        StepVerifier.create(set.concatWith(get)).verifyComplete();


    }
}
