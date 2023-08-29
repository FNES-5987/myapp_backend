package com.jjb.myapp.post;

import com.jjb.myapp.post.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_No(Long postNo);

    Long countByPost_No(Long postNo);
}