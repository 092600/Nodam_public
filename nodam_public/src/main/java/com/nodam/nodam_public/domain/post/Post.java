package com.nodam.nodam_public.domain.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.nodam.nodam_public.domain.comment.Comment;
import com.nodam.nodam_public.domain.time_entity.PostCommentTimeEntity;
import com.nodam.nodam_public.domain.user.User;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;
import lombok.Getter;


@Data
@Entity
@NoArgsConstructor
public class Post {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_num")
    private Long postNum;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String content;

    @Column
    private Long viewCnt;
    
    @Embedded
    private PostCommentTimeEntity postTimeInfo;



    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<Comment>();

}
