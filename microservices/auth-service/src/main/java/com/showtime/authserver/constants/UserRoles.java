package com.showtime.authserver.constants;

import lombok.Getter;

/**
 * @author Vengatesan Nagarajan
 */
@Getter
public enum UserRoles {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String role;

    private UserRoles(String role) {
        this.role = role;
    }

}
