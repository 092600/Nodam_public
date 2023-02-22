package com.nodam.nodam_public.domain.post;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long postCreate(Post post){
        Long postNum = postRepository.save(post).getPostNum();

        return postNum;
    }
    
}
