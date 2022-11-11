package com.devsuperior.workshopmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.workshopmongo.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

}
