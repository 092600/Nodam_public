package com.nodam.nodam_public.domain.post;



import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// domain
import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.comment.Comment;
import com.nodam.nodam_public.domain.timeEntity.CreatedModifiedTimeEntity;

// util
import java.util.List;
import java.util.ArrayList;


@Setter
@Getter
@Entity
@NoArgsConstructor
public class Post extends CreatedModifiedTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_num")
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private Long viewCnt = 0L;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<Comment>();


}
