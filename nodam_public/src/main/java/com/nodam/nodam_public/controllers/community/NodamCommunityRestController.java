package com.nodam.nodam_public.controllers.community;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.post.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v4/community")
@RequiredArgsConstructor
public class NodamCommunityRestController {
    
    private final PostService postService;

    @PostMapping(value = "/post")
    public Long postCreate(Post post){
        System.out.println(post.getWriter());
        System.out.println(post.getTitle());
        System.out.println(post.getContent());

        Long postNum = postService.postCreate(post);

        return postNum;
    }

    // @DeleteMapping(value = "/post")
    // public boolean postDelete(@RequestParam("postNum") Long postNum){
    //     postService.deletePost(postNum);

    //     return true;
    // }

    // @PatchMapping(value = "/post")
    // public boolean postUpdate(@RequestBody PostUpdateDto postUpdateDto){
    //     return postService.updatePost(postUpdateDto);
    // }

    // @PostMapping(value = "/post/search")
    // public int postSearch(@RequestBody SearchPostDto searchDto, Model model){
    //     String searchPostTitle = searchDto.getSearchPostTitle();
    //     String searchPostAuthor = searchDto.getSearchPostAuthor();

    //     if (((searchPostTitle.isEmpty()) && (searchPostAuthor.isEmpty()))) {
    //         return 2;
    //     } else {
    //         return 1;
    //     }
    // }

}
