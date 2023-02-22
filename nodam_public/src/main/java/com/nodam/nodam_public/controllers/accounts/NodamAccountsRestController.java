package com.nodam.nodam_public.controllers.accounts;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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


    // @PostMapping(value = "/login")
    // public boolean login(@RequestBody User loginUser){
    //     if (userService.isExistUserEmail(loginUser.getEmail())){
    //         Optional<User> findUser = userService.findByUserEmail(loginUser.getEmail());


    //         if (findUser.isPresent()) {
    //             if (userService.passwordMatching(loginUser, findUser.get())){
    //                 // SessionUser sessionUser = new SessionUser(findUser);
    //                 httpSession.setAttribute("user", findUser);

    //                 return true;
    //             } else {
    //                 return false;
    //             }

    //         } else {
    //             return false;
    //         }

    //     } else {
    //         return false;
    //     }
    // }


}
