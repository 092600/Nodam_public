package com.nodam.nodam_public.controllers.accounts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/accounts")
@RequiredArgsConstructor
public class NodamAccountsController {

    @GetMapping(value = "/login")
    public String login(@RequestParam(name = "error", required = false) String errorMessage,
                        Model model){

        model.addAttribute("errorMessage", errorMessage);

        return "pages/accounts/login/loginPage";
    }

    @GetMapping(value = "/signup")
    public String signUp(){
        return "pages/accounts/signup/signupPage";
    }

    @GetMapping(value = "/mypage")
    public String myinfo(){
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
