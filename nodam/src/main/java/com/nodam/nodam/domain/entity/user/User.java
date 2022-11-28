package com.nodam.nodam.domain.entity.user;

import com.nodam.nodam.domain.entity.post.Post;
import com.nodam.nodam.domain.entity.user.role.UserRole;
import com.nodam.nodam.domain.entity.user.time.UserTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends UserTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String gender;
    private String profileImage;

    private UserRole userRole = UserRole.User;


    @OneToMany(mappedBy = "postWriter")
    private List<Post> posts = new ArrayList<Post>();
}
