package com.nodam.nodam_public.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nodam.nodam_public.domain.clinic.Clinic;
import com.nodam.nodam_public.domain.clinic.ClinicService;
import com.nodam.nodam_public.domain.clinic.search.SearchClinicDto;
import com.nodam.nodam_public.domain.no_smoke.NoSmokeTryPeople;
import com.nodam.nodam_public.domain.no_smoke.NoSmokeTryPeopleService;

import lombok.RequiredArgsConstructor;


import com.nodam.nodam_public.domain.post.Post;
import com.nodam.nodam_public.domain.post.PostService;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class NodamMainController {

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
    public String goCommunityPage(){
        return "pages/community/community";
    }

    @GetMapping(value = "/community/post/write")
    public String goPostWritePage(){

        return "pages/community/postWritePage";
    }

    @PostMapping(value = "/community/post")
    public String postCreate(Post post){
        System.out.println(post.getWriter());
        System.out.println(post.getTitle());
        System.out.println(post.getContent());

        Long postNum = postService.postCreate(post);

        return "redirect:/community";
    }

    @GetMapping(value = "/community/post/view")
    public String goPostViewPage(Model model
    //, @RequestParam(name = "postNum") Long postNum
                            ){
        // Post post = postService.getPost(postNum);
        // post.setViewCnt(post.getViewCnt() + 1);

        // model.addAttribute("post", postService.getPost(postNum));

        return "pages/community/postViewPage";
    }
}
