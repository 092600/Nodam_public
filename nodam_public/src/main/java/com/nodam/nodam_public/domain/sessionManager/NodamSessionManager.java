package com.nodam.nodam_public.domain.sessionManager;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.nodam.nodam_public.config.authentication.userDetails.NodamUserDetails;
import com.nodam.nodam_public.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NodamSessionManager {
    
    private NodamUserDetails nodamUserDetails;
    private Authentication auth;

    public NodamSessionManager(User user) {
        this.nodamUserDetails = new NodamUserDetails(user);
        this.auth = new UsernamePasswordAuthenticationToken(nodamUserDetails,
                                     null, nodamUserDetails.getAuthorities());
    }
}
