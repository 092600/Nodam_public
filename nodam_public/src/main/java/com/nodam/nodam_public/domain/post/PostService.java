package com.nodam.nodam_public.domain.post;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nodam.nodam_public.domain.post.search.SearchPostDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long postCreate(Post post){
        Long postNum = postRepository.save(post).getId();

        return postNum;
    }

    public Page<Post> getPostPages(SearchPostDto searchPostDto){

        Pageable pageable = PageRequest.of(searchPostDto.getPage(),
                                            searchPostDto.getRecordSize(),
                                            Sort.by("id").descending());

        return postRepository.findAll(pageable);
    }

    public Optional<Post> getPostById(Long postNum) {
    return postRepository.findById(postNum);
    }

    public void deletePostById(Long postNum) {
    postRepository.deleteById(postNum);
    }

    @Transactional
    public void updatePost(Post post) {
    postRepository.postUpdate(post.getTitle(), post.getContent(), LocalDateTime.now(), post.getId());
    }


    @Transactional
    public void viewPost(Post post) {
        postRepository.viewPost(post.getViewCnt()+1, post.getId());

    }

    public Page<Post> searchPosts(SearchPostDto searchDto, Pageable pageable){
        
        Page<Post> postList; 

        if (!(searchDto.getTitle().isEmpty()) && (searchDto.getWriter().isEmpty())){
            postList = postRepository.findAllByTitle(searchDto.getTitle(), pageable);

        }
         else if ((searchDto.getTitle().isEmpty()) && (!searchDto.getWriter().isEmpty()) ){
            postList = postRepository.findAllByWriter(searchDto.getWriter(), pageable);

        } else if ((!searchDto.getTitle().isEmpty()) && (!searchDto.getWriter().isEmpty())){
            postList = postRepository.findAllByTitleAndWriter(
                    searchDto.getTitle(), searchDto.getWriter(), pageable);
        } else {
            postList = getPostPages(searchDto);
        }

        return postList;
    }


    public List<Post> find6PostsOrderByViewCnt(){
        return postRepository.findTop6ByOrderByViewCntDesc();
    }

    public List<Post> find6PostsOrderByCreatedDate(){
        return postRepository.findTop6ByOrderByCreatedDate();
    }

}
