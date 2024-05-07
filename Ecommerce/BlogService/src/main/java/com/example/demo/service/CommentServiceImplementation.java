package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.entity.comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImplementation implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	
	public comment createComment(Long postId,String postedBy, String content) {
		Optional<Post> optionalPost = postRepository.findById(postId);
		if(optionalPost.isPresent()) {
			comment comment = new comment();
			comment.setPost(optionalPost.get());
			comment.setContent(content);
			comment.setPostedBy(postedBy);
			comment.setCreatedAt(new Date());
			return commentRepository.save(comment);
		}
		throw new EntityNotFoundException("post not found");
	}
	
	public List<comment> getCommentsByPostId(Long postId){
		return commentRepository.findByPostId(postId);
	}
}
