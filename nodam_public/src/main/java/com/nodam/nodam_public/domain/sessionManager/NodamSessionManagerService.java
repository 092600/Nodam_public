package com.nodam.nodam_public.domain.sessionManager;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nodam.nodam_public.domain.user.User;


@Service
public class NodamSessionManagerService {
    
    public void updateNodamUserDetailsSession(User user, HttpSession session) {
        NodamSessionManager nsm = new NodamSessionManager(user);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(nsm.getAuth());
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);;
    }
    
}
