package com.devsuperior.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.devsuperior.workshopmongo.entities.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String>{

	@Query("{ 'email': { $regex: ?0, $options: 'i' } }")
	Mono<User> searchEmail(String email);
}
