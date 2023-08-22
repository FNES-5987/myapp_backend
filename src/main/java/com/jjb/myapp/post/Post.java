package com.jjb.myapp.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private String creatorName;
    private Long createdTime;
}
