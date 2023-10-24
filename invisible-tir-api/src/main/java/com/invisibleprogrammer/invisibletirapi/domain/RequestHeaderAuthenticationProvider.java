package com.invisibleprogrammer.invisibletirapi.domain;

import com.invisibleprogrammer.invisibletirapi.domain.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collections;

public class RequestHeaderAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;

    public RequestHeaderAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authSecretKey = String.valueOf(authentication.getPrincipal());

        // Todo: validate token, get user principal
        // Question: do we need to check the user at all the requests or can we do some session-based stuff?

//        if(StringUtils.isBlank(authSecretKey) || !authSecretKey.equals(apiAuthSecret)) {
        if(StringUtils.isBlank(authSecretKey) || !authSecretKey.equals("dAtAwbXQOqkzsbxCvgm0q")) {
            throw new BadCredentialsException("Bad Request Header Credentials");
        }

        return new PreAuthenticatedAuthenticationToken(authentication.getPrincipal(), null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
