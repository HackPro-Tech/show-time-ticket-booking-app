package com.showtime.authserver.service.impl;

import com.showtime.authserver.domain.UserDetailsPrincipal;
import com.showtime.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Vengatesan Nagarajan
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    // Fetch User based on Email Address or Phone No
    @Override
    public UserDetailsPrincipal loadUserByUsername(String emailOrPhoneNo) {
        UserDetailsPrincipal user = userService.getUserByEmailOrPhoneNo(emailOrPhoneNo);
        if (user == null) {
            throw new UsernameNotFoundException("User not available, please check Email or PhoneNo");
        }
        return user;
    }
}
