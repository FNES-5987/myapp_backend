package com.jjb.myapp.post;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String nickname;
    private String password;
    private Long createdTime;

    @ManyToOne
    private Post post;
}
