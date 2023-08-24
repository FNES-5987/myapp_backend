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
        List<Post> list = repo.findAll(Sort.by("no").ascending());
        System.out.println(list);
        return list;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost(@RequestBody Post post) {

        // post 내용 중 title이나 content의 내용이 없을 시 400 BAD_REQUEST 반환
        if ((post.getTitle() == null) || (post.getContent() == null) || post.getTitle().isEmpty() || post.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // 포스트 작성 당시 시간으로 CreatedTime 설정
        post.setCreatedTime(new Date().getTime());

        Post savedPost = repo.save(post);

        // 내용이 정상적으로 들어가있다면 201 CREATED 반환
        if (savedPost != null) {
            Map<String, Object> res = new HashMap<>();
            res.put("data", savedPost);
            res.put("message", "created");

            //
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{no}")
    public ResponseEntity removePost(@PathVariable long no) {
        System.out.println(no);

        if (!repo.findById(no).isPresent()) {
            // Response Status code : 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 객체(리소스) 삭제
        repo.deleteById(no);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{no}")
    public ResponseEntity<Post> updatePost(@PathVariable Long no, @RequestBody Post updatedPost) {
        Optional<Post> post = repo.findById(no);
        if (post.isPresent()) {
            Post existingPost = post.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());

            Post savedPost = repo.save(existingPost);
            return ResponseEntity.ok(savedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

