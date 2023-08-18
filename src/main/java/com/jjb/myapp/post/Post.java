package com.jjb.myapp.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long no;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private String creatorName;
    private Long createdTime;



}
