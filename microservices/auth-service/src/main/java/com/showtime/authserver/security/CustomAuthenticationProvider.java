package com.showtime.authserver.security;

import com.showtime.authserver.domain.UserDetailsPrincipal;
import com.showtime.authserver.service.UserService;
import com.showtime.authserver.utils.SecurityHelper;
import com.showtime.exception.IAMAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Vengatesan Nagarajan
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        // Check whether the UserName(Email or PhoneNo) and Password are Valid
        checkUserNameAndPasswordIsValid(authentication);

        String emailOrPhoneNo = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Fetch User Details Based on Email Or PhoneNo
        UserDetailsPrincipal user = userService.getUserByEmailOrPhoneNo(emailOrPhoneNo);

        // Check whether the User is Exist or Not
        checkUserNameAndPasswordIsExist(user);

        // Check Whether the Password is Matched or Not
        return checkPasswordIsMatched(user, emailOrPhoneNo, password);

    }

    private void checkUserNameAndPasswordIsValid(Authentication authentication) {
        if (authentication.getName() == null || "".equals(authentication.getName())) {
            throw new IAMAuthenticationException("Username should not blank");
        } else if (authentication.getCredentials() == null || "".equals(authentication.getCredentials().toString())) {
            throw new IAMAuthenticationException("Password should not blank");
        }
    }

    private void checkUserNameAndPasswordIsExist(UserDetailsPrincipal user) {
        if (user == null) {
            throw new IAMAuthenticationException("Invalid Email/PhoneNo!!!");
        } else if (!user.isEnabled()) {
            throw new IAMAuthenticationException("Account is blocked, Please contact admin");
        }
    }

    private UsernamePasswordAuthenticationToken checkPasswordIsMatched(UserDetailsPrincipal user, String emailOrPhone,
                                                                       String password) {
        if (SecurityHelper.isPasswordMatched(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(emailOrPhone, password, user.getAuthorities());
        } else {
            throw new IAMAuthenticationException("Invalid Password!!!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
