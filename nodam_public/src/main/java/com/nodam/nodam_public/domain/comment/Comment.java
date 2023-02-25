package com.nodam.nodam_public.domain.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.timeEntity.TimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Embedded;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeEntity {

    @Id @GeneratedValue
    @Column(name = "post_num")
    private Long id;

    private String content;

    
    @ManyToOne
    private Post post;
}
