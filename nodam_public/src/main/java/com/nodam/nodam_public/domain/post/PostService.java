package com.nodam.nodam_public.domain.post;



import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

// 
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

// Pageable
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// DTO 
import com.nodam.nodam_public.domain.post.search.SearchPostDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Optional<Post> findByPostId(Long id) {
        return postRepository.findById(id);
    
    }

    // create post
    @Transactional
    public Long postCreate(Post post){
        Long postNum = postRepository.save(post).getId();

        return postNum;
    }

    // community get Page<post> obj
    public Page<Post> getPostPages(SearchPostDto searchPostDto){

        Pageable pageable = PageRequest.of(searchPostDto.getPage(),
                                            searchPostDto.getRecordSize(),
                                            Sort.by("id").descending());

        return postRepository.findAll(pageable);
    }


    // update post
    @Transactional
    public void updatePost(Post post) {
        postRepository.postUpdate(post.getTitle(), post.getContent(), LocalDateTime.now(), post.getId());
    }

    // delete post
    public void deletePostById(Long postNum) {
        postRepository.deleteById(postNum);
    }

    // viewCnt +=1 
    @Transactional
    public void viewPost(Post post) {
        postRepository.viewPost(post.getViewCnt()+1, post.getId());

    }

    //search Post
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


    // mainPage 
    public List<Post> find6PostsOrderByViewCnt(){
        return postRepository.findTop6ByOrderByViewCntDesc();
    }

    public List<Post> find6PostsOrderByCreatedDate(){
        return postRepository.findTop6ByOrderByCreatedDateDesc();
    }

}
