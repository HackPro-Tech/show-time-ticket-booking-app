package com.showtime.authserver.domain;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Data
@Table(value = "user")
public class User {

    @PrimaryKey("user_id")
    @CassandraType(type = DataType.Name.UUID)
    private UUID userId;

    @Column("username")
    @CassandraType(type = DataType.Name.TEXT)
    private String username;

    @Column("email")
    @CassandraType(type = DataType.Name.TEXT)
    private String email;

    @Column("password")
    @CassandraType(type = DataType.Name.TEXT)
    private String password;

    @Column("phone_no")
    @CassandraType(type = DataType.Name.TEXT)
    private String phoneNo;

    @Column("active")
    @CassandraType(type = DataType.Name.BOOLEAN)
    private boolean enabled;

    @Column("roles")
    @CassandraType(type = DataType.Name.SET, typeArguments = {DataType.Name.TEXT})
    private Set<String> roles;

    @Column("account_non_expired")
    @CassandraType(type = DataType.Name.BOOLEAN)
    private boolean accountNonExpired;

    @Column("account_non_locked")
    @CassandraType(type = DataType.Name.BOOLEAN)
    private boolean accountNonLocked;

    @Column("credentials_non_expired")
    @CassandraType(type = DataType.Name.BOOLEAN)
    private boolean credentialsNonExpired;

}
