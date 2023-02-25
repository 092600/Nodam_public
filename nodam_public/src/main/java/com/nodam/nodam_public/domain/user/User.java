package com.nodam.nodam_public.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.timeEntity.UserTimeEntity;
import com.nodam.nodam_public.domain.user.info.UserInfo;
import com.nodam.nodam_public.domain.user.role.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends UserTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String profileImage;




    @Embedded
    private UserInfo userInfo;


    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<Post>();

}
