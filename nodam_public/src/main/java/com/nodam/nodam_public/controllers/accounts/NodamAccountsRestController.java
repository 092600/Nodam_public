package com.nodam.nodam_public.controllers.accounts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.user.UserInfo;
import com.nodam.nodam_public.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v4/accounts")
@RequiredArgsConstructor
public class NodamAccountsRestController {

    private final UserService userService;
    
    @GetMapping(value = "/signup/checkEmail")
    public boolean checkEmail(@RequestParam("email") String email){
        return userService.isExistUserEmail(email);
    }

    @PostMapping(value = "/signup")
    public boolean signUp(@RequestBody User user){
        
        System.out.println(user.getUserInfo().getGender());
        if (userService.isExistUserEmail(user.getEmail())){
            return false;
        } else {
            return userService.signUp(user);
        }
    }

}
