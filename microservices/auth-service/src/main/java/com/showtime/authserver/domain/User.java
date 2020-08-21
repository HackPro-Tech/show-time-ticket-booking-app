package com.showtime.authserver.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Getter
@Setter
@NoArgsConstructor
@Table(value = "user")
public class User {

    @PrimaryKey("user_id")
    private UUID userId;

    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("phone_no")
    private String phoneNo;

    @Column("active")
    private boolean enabled;

    @Column("roles")
    private Set<String> roles;

    @Column("account_non_expired")
    private boolean accountNonExpired;

    @Column("account_non_locked")
    private boolean accountNonLocked;

    @Column("credentials_non_expired")
    private boolean credentialsNonExpired;

}
