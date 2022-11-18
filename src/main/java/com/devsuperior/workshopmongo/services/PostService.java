package com.devsuperior.workshopmongo.services;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.workshopmongo.dto.PostDTO;
import com.devsuperior.workshopmongo.entities.Post;
import com.devsuperior.workshopmongo.repositories.PostRepository;
import com.devsuperior.workshopmongo.services.exceptioons.ResourceNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Mono<PostDTO> findById(String id) throws InterruptedException, ExecutionException {
		Post post = repository.findById(id).toFuture().get();
		System.out.println(post.toString());
		
		return repository.findById(id)
				.map(existingPost -> new PostDTO(existingPost))
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
	}
	
	public Flux<PostDTO> findByTitle(String text) {
		return repository.searchTitle(text)
				.map(postFound -> new PostDTO(postFound));
	}
	
	public Flux<PostDTO> fullSearch(String text, Instant minDate, Instant maxDate) {
		maxDate = maxDate.plusSeconds(86400);
		return repository.fullSearch(text, minDate, maxDate)
				.map(postFound -> new PostDTO(postFound));
	}
	
	public Flux<PostDTO> findByUser(String id) {	
		return repository.findByUser(new ObjectId(id))
				.map(post -> new PostDTO(post));
	}
}
