package com.nodam.nodam_public.domain.post;


import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;




public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByWriter(String writer, Pageable pageable);
    Page<Post> findAllByTitleAndWriter(String title, String writer, Pageable pageable);

    // List<Post> find(String title, Pageable pageable);
    Page<Post> findAllByTitle(String title, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Post p SET p.title = :title, p.content = :content, p.modifiedDate = :modifiedDate WHERE p.id = :id")
    public void postUpdate(@Param("title")  String title, @Param("content") String content,
                @Param("modifiedDate") LocalDateTime ldt, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Post p SET p.viewCnt = :viewCnt WHERE p.id = :id")
    public void viewPost(@Param("viewCnt") Long viewCnt, @Param("id") Long id);

    List<Post> findTop6ByOrderByViewCntDesc();
    List<Post> findTop6ByOrderByCreatedDateDesc();


}
