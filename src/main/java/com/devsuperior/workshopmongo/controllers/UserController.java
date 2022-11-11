package com.devsuperior.workshopmongo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.workshopmongo.dto.UserDTO;
import com.devsuperior.workshopmongo.services.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public Flux<UserDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<UserDTO>> findById(@PathVariable String id) {
		return service.findById(id).map(userDto -> ResponseEntity.ok().body(userDto));
	}
	
	@PostMapping
	public Mono<ResponseEntity<UserDTO>> insert(@RequestBody UserDTO dto, UriComponentsBuilder builder) {
		return service.insert(dto).map(newUser -> ResponseEntity
				.created(builder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri()).body(newUser));
	}
	
	@PutMapping(value = "/{id}")
	public Mono<ResponseEntity<UserDTO>> update(@PathVariable String id, @RequestBody UserDTO dto) {
		return service.update(id, dto).map(userUpdated -> ResponseEntity.ok().body(userUpdated));
	}
	
	@DeleteMapping(value = "/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		return service.delete(id).then(Mono.just(ResponseEntity.noContent().<Void>build()));
	}
}
