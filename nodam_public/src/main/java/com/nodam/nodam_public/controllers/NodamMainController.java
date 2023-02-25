package com.nodam.nodam_public.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import com.nodam.nodam_public.domain.clinic.Clinic;
import com.nodam.nodam_public.domain.clinic.ClinicService;
import com.nodam.nodam_public.domain.clinic.search.SearchClinicDto;
import com.nodam.nodam_public.domain.no_smoke.NoSmokeTryPeople;
import com.nodam.nodam_public.domain.no_smoke.NoSmokeTryPeopleService;

import lombok.RequiredArgsConstructor;


import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.post.PostService;
import com.nodam.nodam_public.domain.post.search.SearchPostDto;
import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.user.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
@RequiredArgsConstructor
public class NodamMainController {

    private final UserService userService;
    private final PostService postService;
    private final ClinicService clinicService;
    private final NoSmokeTryPeopleService noSmokeTryPeopleService;



    @GetMapping(value = "/")
    public String goMainPage(Model model) {

    
        Optional<NoSmokeTryPeople> nstp =  noSmokeTryPeopleService.getTodayNoSmokeTryPeople();

        if (nstp.isPresent()) {
            model.addAttribute("amountOfQuitSuccessSmokePeople", nstp.get().getAmountOfTryPeople());
            model.addAttribute("amountOfQuitTrySmokePeople", nstp.get().getAmountOfQuitPeople());
        }

        model.addAttribute("viewCntTop6Posts", postService.find6PostsOrderByViewCnt());
        model.addAttribute("createDateTop6Posts", postService.find6PostsOrderByCreatedDate());
        // model.addAttribute("userNoSmokingDate", user.getUserInfo().getNoSmokingDate());

        return "pages/main/mainPage";
    }

    @GetMapping(value = "/dashboard/{num}")
    public String goDashboardPage(@PathVariable String num){
        return "pages/dashboard/dashboard"+num+"Page";
    }

    @GetMapping(value = "/smokingmap")
    public String goSmokingmapPage(){
        return "pages/smokingmap/smokingmapPage";
    }


    @GetMapping(value = "/clinic")
    public String goClinicPage(SearchClinicDto searchClinicDto, Model model){

        Page<Clinic> clinics = clinicService.findAllByRegion(searchClinicDto);


        model.addAttribute("clinics", clinics);
        model.addAttribute("region", searchClinicDto.getRegion());

        return "pages/clinic/clinicPage";
    }

    @GetMapping(value = "/community")
    public String goCommunityPage(SearchPostDto searchPostDto, Model model){

        Page<Post> posts = postService.getPostPages(searchPostDto);

        model.addAttribute("posts", posts);
        model.addAttribute("trForNumber", searchPostDto.getRecordSize() - posts.getNumberOfElements());

        return "pages/community/community";
    }

    @GetMapping(value = "/community/post/write")
    public String goPostWritePage(){

        return "pages/community/postWritePage";
    }


    @GetMapping(value = "/community/post/view")
    public String goPostViewPage(Model model, @RequestParam(name = "id") Long postNum){

        Optional<Post> findPost = postService.getPostById(postNum);
        if (findPost.isPresent()) {
            postService.viewPost(findPost.get());

            model.addAttribute("post",  findPost.get());

            return "pages/community/postViewPage";
        }

        return "/pages/community/community";
    }


    @GetMapping(value = "/community/post/update")
    public String goPostUpdatePage(Model model, @RequestParam(name = "id") Long postNum){

        Optional<Post> findPost = postService.getPostById(postNum);
        if (findPost.isPresent()) {
            model.addAttribute("post",  findPost.get());

            return "pages/community/postUpdatePage";
        }

        return "/pages/community/community";
    }

    @GetMapping(value = "/community/post/search")
    public String searchAndGoCommunityPage(@RequestParam(value = "title", required = false) String title,
                                           @RequestParam(value = "writer", required = false) String writer,
                                           @RequestParam("recordSize") Integer recordSize, Model model,
                            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        
        Page<Post> searchPostPage = postService.searchPosts(
            new SearchPostDto(recordSize, title, writer), pageable);
        
        model.addAttribute("posts", searchPostPage);
        model.addAttribute("trForNumber", recordSize - searchPostPage.getNumberOfElements());

        return "/pages/community/community";
    }

    @GetMapping(value = "/nosmoking")
    public String getNosmokingPopUp(){
        
        return "/pages/main/nosmoking/nosmoking";
    }

}
