package com.nodam.nodam_public.domain.user;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.transaction.Transactional;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import java.util.Optional;

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

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }


    @Transactional
    public Date noSmoking(Long id) {
        Date date = new Date();
        userRepository.noSmoking(date, id);

        return date;
    }

    @Transactional
    public void noSmokingStop(Long id) {
        userRepository.noSmoking(null, id);
    }

   


    // accounts 
    public boolean matchingPassword(User tmp) {
        Optional<User> optionalUser = userRepository.findByEmail(tmp.getEmail());
        User user = optionalUser.get();

        if (passwordEncoder.matches(tmp.getPassword(), user.getPassword())) {
            return true;
        } else {
            return false;
        }
        
    }

    public boolean changePassword(User tmp) {
        try {
            Optional<User> optionalUser = userRepository.findByEmail(tmp.getEmail());
            User user = optionalUser.get();
                
            String updatePassword = passwordEncoder.encode(user.getPassword());
            userRepository.updatePassword(updatePassword, user.getEmail());
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
