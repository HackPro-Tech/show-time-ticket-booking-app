package com.showtime.authserver.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Vengatesan Nagarajan
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityHelper {

    public static final int ROUNDS = 12;

    public static boolean isPasswordMatched(final String plainPassword, final String bCryptHash) {
        return new BCryptPasswordEncoder(ROUNDS).matches(plainPassword, bCryptHash);
    }

}
