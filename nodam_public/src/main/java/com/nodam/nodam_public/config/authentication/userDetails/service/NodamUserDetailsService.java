package com.nodam.nodam_public.config.authentication.userDetails.service;

import com.nodam.nodam_public.config.authentication.userDetails.NodamUserDetails;
import com.nodam.nodam_public.domain.user.User;
import com.nodam.nodam_public.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NodamUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("didn't found user");
        }

        return new NodamUserDetails(user.get());
    }
}
