package com.nodam.nodam_public.controllers.api;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;

import com.nodam.nodam_public.config.authentication.userDetails.NodamUserDetails;
import com.nodam.nodam_public.domain.clinic.Clinic;
import com.nodam.nodam_public.domain.clinic.ClinicService;
import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.user.UserService;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v4")
@RequiredArgsConstructor
public class NodamRestController {
    
    private final UserService userService;
    private final ClinicService clinicService;

    @GetMapping(value = "/clinic")
    public List<Clinic> getClinicData(@RequestParam(name = "region") String region,
                                 Model model){
        // System.out.println(region);
        List<Clinic> Clinics = clinicService.findByClinicRegion(region);
        return Clinics;
    }

    @PostMapping(value = "/nosmoking")
    public Date nosmoking(@RequestBody User tmp, HttpSession session){
        Optional<User> user = userService.findByUserId(tmp.getId());

        try {
            if (user.isPresent()) {
                Date date = userService.noSmoking(user.get().getId());
                user.get().getUserInfo().setNoSmokingDate(date);
                
                // session
                NodamUserDetails nud = new NodamUserDetails(user.get());
                Authentication auth = new UsernamePasswordAuthenticationToken(nud, null, nud.getAuthorities());

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(auth);
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);;
                //

                
                return date;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            
            return null;
        }
    }

    @PostMapping(value = "/noSmokingStop")
    public String noSmokingStop(@RequestBody User tmp, HttpSession session) {
        Optional<User> OptionalUser = userService.findByUserId(tmp.getId());

        try {
            if (OptionalUser.isPresent()) {
                User user = OptionalUser.get();
                    
                Date noSmokingStartDate = user.getUserInfo().getNoSmokingDate();
                if (noSmokingStartDate != null) {
                    Date now = new Date();
                    userService.noSmokingStop(user.getId());

                    Long sec = (now.getTime() - noSmokingStartDate.getTime()) / 1000;
                    String days = String.valueOf(sec / (24*60*60)); // 일자수
    
                    // session
                    user.getUserInfo().setNoSmokingDate(null);

                    NodamUserDetails nud = new NodamUserDetails(user);
                    Authentication auth = new UsernamePasswordAuthenticationToken(nud, null, nud.getAuthorities());
    
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(auth);
                    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);;
                    //

                    return days;
                }
            }
            
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
