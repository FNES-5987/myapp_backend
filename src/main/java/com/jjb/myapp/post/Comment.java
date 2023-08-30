package com.jjb.myapp.post;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Reference;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}
