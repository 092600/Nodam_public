package com.nodam.nodam_public.domain.comment;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.timeEntity.CreatedTimeEntity;
import com.nodam.nodam_public.domain.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Comment extends CreatedTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String writer;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_num")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
