package com.nodam.nodam_public.controllers.api;


import java.util.List;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.nodam.nodam_public.domain.clinic.Clinic;
import com.nodam.nodam_public.domain.clinic.ClinicService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v4")
@RequiredArgsConstructor
public class NodamRestController {
    
    private final ClinicService clinicService;


    // 클리닉 데이터 받아오기
    @GetMapping(value = "/clinic")
    public List<Clinic> getClinicData(@RequestParam(name = "region") String region,
                                 Model model){
        List<Clinic> Clinics = clinicService.findByClinicRegion(region);
        return Clinics;
    }


    
}
