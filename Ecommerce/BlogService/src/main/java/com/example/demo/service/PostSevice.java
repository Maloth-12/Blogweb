package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Post;

public interface PostSevice {
 
	Post savePost(Post post);
	List<Post> getAllPost();
	Post getPostById(Long postId);
	Post likePost(Long postId);
	List<Post> searchByName(String name);
}
