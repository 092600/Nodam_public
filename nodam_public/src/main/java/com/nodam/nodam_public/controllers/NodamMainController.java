package com.nodam.nodam_public.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;


import com.nodam.nodam_public.domain.clinic.Clinic;
import com.nodam.nodam_public.domain.clinic.ClinicService;
import com.nodam.nodam_public.domain.clinic.search.SearchClinicDto;
import com.nodam.nodam_public.domain.no_smoke.NoSmokeTryPeople;
import com.nodam.nodam_public.domain.no_smoke.NoSmokeTryPeopleService;

import lombok.RequiredArgsConstructor;;

@Controller
@RequiredArgsConstructor
public class NodamMainController {

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
}
