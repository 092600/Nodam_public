package com.nodam.nodam_public.controllers.community;


import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nodam.nodam_public.domain.comment.Comment;
import com.nodam.nodam_public.domain.comment.CommentService;
import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.post.PostService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v4/community")
@RequiredArgsConstructor
public class NodamCommunityRestController {
    
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping(value = "/post")
    public Boolean postCreate(@RequestBody Post post){
        try {
            postService.postCreate(post);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @DeleteMapping(value = "/post")
    public boolean postDelete(@RequestParam("id") Long postNum, Authentication auth){
        try {
            Optional<Post> findPost = postService.getPostById(postNum);

            if (findPost.isPresent()) {
                Post post = findPost.get();
                if (post.getWriter().equals(auth.getName())) {
                    postService.deletePostById(post.getId());
                    
                    return true;
                }
            } 

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            
            return false;
        }
    }


    @PatchMapping(value = "/post")
    public boolean postUpdate(@RequestBody Post updatePost, Authentication auth){
        try {
            Optional<Post> findPost = postService.getPostById(updatePost.getId());

            if (findPost.isPresent()) {
                Post post = findPost.get();

                if (post.getWriter().equals(auth.getName())) {
                    postService.updatePost(updatePost);

                    return true;
                }
            } 

            return false;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            
            return false;
        }
    }

    @PostMapping(value = "/post/comment")
    public boolean createComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @DeleteMapping(value = "/post/comment")
    public boolean deleteComment(@RequestBody Comment comment) {
        System.out.println(comment.getWriter());
        System.out.println(comment.getId());
        System.out.println(comment.getPost().getId());

        
        return commentService.deleteById(comment);
    }
}
