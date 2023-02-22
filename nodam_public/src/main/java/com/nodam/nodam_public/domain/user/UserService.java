package com.nodam.nodam_public.domain.user;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean isExistUserEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean signUp(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());

            return false;
        }
    }
}
