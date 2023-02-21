package com.nodam.nodam_public.controllers.accounts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/accounts")
@RequiredArgsConstructor
public class NodamAccountsController {
 
    @GetMapping(value = "/login")
    public String login(){
        return "pages/accounts/login/loginPage";
    }

    @GetMapping(value = "/signup")
    public String signUp(){
        return "pages/accounts/signup/signupPage";
    }

    @GetMapping(value = "/myinfo")
    public String myinfo(){
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return "pages/accounts/mypage/myPage";
    }

    @GetMapping(value = "/findId")
    public String accountsFindId(){
        return "pages/accounts/find/findIdPage";
    }

    @GetMapping(value = "/findPw")
    public String accountsFindPw(){
        return "pages/accounts/find/findPasswordPage";
    }
}
