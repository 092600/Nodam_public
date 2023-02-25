package com.nodam.nodam_public.controllers.accounts;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.user.UserService;
import com.nodam.nodam_public.domain.user.info.UserInfo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v4/accounts")
@RequiredArgsConstructor
public class NodamAccountsRestController {

    private final UserService userService;

    private final HttpSession httpSession;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/signup/checkEmail")
    public boolean checkEmail(@RequestParam("email") String email){
        return userService.isExistUserEmail(email);
    }

    @PostMapping(value = "/signup")
    public boolean signUp(@RequestBody User user){

        if (userService.isExistUserEmail(user.getEmail())){
            return false;
        } else {
            return userService.signUp(user);
        }
    }


    @PostMapping(value = "/mypage/certification")
    public boolean certificateUser(@RequestBody User tmp, Authentication auth) {
        if (auth.getName().equals(tmp.getEmail())) {
            return userService.matchingPassword(tmp);
        } 

        return false;
    }

    @PatchMapping(value = "/mypage/password")
    public boolean changePassword(User tmp, Authentication auth) {
        if (auth.getName().equals(tmp.getName())) {
            userService.changePassword(tmp);
        }

        return true;
    }
    
}
