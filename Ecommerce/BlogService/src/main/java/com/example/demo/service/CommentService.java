package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.comment;

public interface CommentService {

	comment createComment(Long postId,String postedBy, String content);
	List<comment> getCommentsByPostId(Long postId);
}
