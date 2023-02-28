package com.nodam.nodam_public.domain.comment;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.post.PostService;
import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;

    @Transactional
    public boolean save(Comment comment) {
        try {
            Optional<Post> optionalPost = postService.findByPostId(comment.getPost().getId());
            Optional<User> optionalUser = userService.findByEmail(comment.getWriter());

            if (optionalPost.isPresent() && optionalUser.isPresent()) {
                this.addCommentNPost(comment, optionalUser.get(), optionalPost.get());

                return true;
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean deleteById(Comment comment) {
        try {
            Optional<Post> optionalPost = postService.findByPostId(comment.getPost().getId());
            Optional<User> optionalUser = userService.findByEmail(comment.getWriter());

            this.delCommentNPost(comment, optionalUser.get(), optionalPost.get());
            commentRepository.deleteById(comment.getId());

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }


    public void addCommentNPost(Comment comment, User user, Post post) {
        post.getComments().add(comment);
        user.getComments().add(comment);

        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);
    }

    public void delCommentNPost(Comment comment, User user, Post post) {
        post.getComments().remove(comment);
        user.getComments().remove(comment);

        comment.setUser(null);
        comment.setPost(null);

        // commentRepository.deleteById(comment.getId());

    }
}
