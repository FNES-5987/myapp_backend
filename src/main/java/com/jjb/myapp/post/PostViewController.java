package com.jjb.myapp.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/post-view")
public class PostViewController {

    @Autowired
    PostRepository repo;

    @GetMapping(value = "/{no}")
    public ResponseEntity<Post> getPostByNo(@PathVariable long no) {
        Optional<Post> optionalPost = repo.findById(no);

        if (optionalPost.isPresent()) {
            return ResponseEntity.ok(optionalPost.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

