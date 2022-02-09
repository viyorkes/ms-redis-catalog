package com.redis.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

@SpringBootTest
class RedisCatalogApplicationTests {

	@Autowired
	private ReactiveStringRedisTemplate template;

	@Test
	void contextLoads() {

		ReactiveValueOperations<String,String> valueOperations = this.template.opsForValue();

		Mono<Void> mono = Flux.range(1, 5000_0)
				.flatMap(i -> valueOperations.increment("user:1:visit"))
				.then();
		StepVerifier.create(mono)
				.verifyComplete();
	}

}
