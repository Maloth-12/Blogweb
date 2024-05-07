package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.comment;

@Repository
public interface CommentRepository extends JpaRepository<comment,Long>{

	List<comment> findByPostId(Long postId);
}
