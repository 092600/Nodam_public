package com.nodam.nodam_public.config.authentication.userDetails.service;

import com.nodam.nodam_public.config.authentication.userDetails.NodamUserDetails;
import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodamUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("didn't found user");
        }

        return new NodamUserDetails(user);
    }
}
