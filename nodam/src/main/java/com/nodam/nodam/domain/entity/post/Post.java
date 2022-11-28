package com.nodam.nodam.domain.entity.post;

import com.nodam.nodam.domain.entity.post.time.PostTimeEntity;
import com.nodam.nodam.domain.entity.user.User;

import javax.persistence.*;

@Entity
public class Post extends PostTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long post_num;

    private Long viewCnt;

    private String title;
    private String writer;
    private String content;

    @ManyToOne
    @JoinColumn(name = "posts")
    private User postWriter;
}
