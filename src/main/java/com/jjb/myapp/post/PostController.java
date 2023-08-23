package com.jjb.myapp.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    PostRepository repo;


    @GetMapping
    public List<Post> getPostList() {

//        List<Post> list = repo.findPostSortByNo();
        List<Post> list = repo.findAll(Sort.by("no").ascending());
        System.out.println(list);
        return list;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost(@RequestBody Post post) {

        if ((post.getTitle() == null) || (post.getContent() == null) || post.getTitle().isEmpty() || post.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        post.setCreatedTime(new Date().getTime());

        Post savedPost = repo.save(post);


        if (savedPost != null) {
            Map<String, Object> res = new HashMap<>();
            res.put("data", savedPost);
            res.put("message", "created");

            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }


        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{no}")
    public ResponseEntity removePost(@PathVariable long no) {
        System.out.println(no);

//        Optional<Post> post = repo.findPostByNo(no);
//
//
//        if (!post.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        repo.deleteById(no);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
        if (!repo.findById(no).isPresent()) {
            // Response Status code : 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 객체(리소스) 삭제
        repo.deleteById(no);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}