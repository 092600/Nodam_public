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


    // 글 등록
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


    // post 업데이트
    @PatchMapping(value = "/post")
    public boolean postUpdate(@RequestBody Post updatePost, Authentication auth){
        try {
            Optional<Post> findPost = postService.findByPostId(updatePost.getId());

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


    // 글 삭제
    @DeleteMapping(value = "/post")
    public boolean postDelete(@RequestParam("id") Long postNum, Authentication auth){
        try {
            Optional<Post> findPost = postService.findByPostId(postNum);

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

    // 댓글 등록
    @PostMapping(value = "/post/comment")
    public boolean createComment(@RequestBody Comment comment) {

        return commentService.save(comment);
    }

    // 댓글 삭제
    @DeleteMapping(value = "/post/comment")
    public boolean deleteComment(@RequestBody Comment comment) {
        
        return commentService.deleteById(comment);
    }
}
