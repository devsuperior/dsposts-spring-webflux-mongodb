package com.devsuperior.workshopmongo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.workshopmongo.dto.PostDTO;
import com.devsuperior.workshopmongo.services.PostService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService service;
	
	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<PostDTO>> findById(@PathVariable String id) {
		return service.findById(id).map(postDto -> ResponseEntity.ok().body(postDto));
	}
}
