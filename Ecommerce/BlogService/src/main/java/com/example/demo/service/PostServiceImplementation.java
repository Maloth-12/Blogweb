package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostServiceImplementation implements PostSevice{

	@Autowired
	private PostRepository postRepository;
	
	public Post savePost(Post post) {
		post.setLikeCount(0);
		post.setViewCount(0);
		post.setDate(new Date());
		return postRepository.save(post);
	}
	
	public List<Post> getAllPost() {
		return postRepository.findAll();
	}
	public Post getPostById(Long postId) {
		Optional<Post> optionalPost = postRepository.findById(postId);
		if(optionalPost.isPresent()) {
			Post post = optionalPost.get();
			post.setViewCount(post.getViewCount()+1);
			return postRepository.save(post);
		}
		else {
			throw new EntityNotFoundException("post not found");
		}
		
	}
	public Post likePost(Long postId) {
		Optional<Post> optionalPost = postRepository.findById(postId);
		if(optionalPost.isPresent()) {
			Post post = optionalPost.get();
			post.setLikeCount(post.getLikeCount()+1);
			return postRepository.save(post);
		}
		else {
			throw new EntityNotFoundException("post not found"+ postId);
		}
		
	}
	public List<Post> searchByName(String name) {
		return postRepository.findAllByNameContaining(name);
	}
}
