package com.nodam.nodam_public.domain.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.timeEntity.TimeEntity;
import com.nodam.nodam_public.domain.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String writer;
    private String content;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
