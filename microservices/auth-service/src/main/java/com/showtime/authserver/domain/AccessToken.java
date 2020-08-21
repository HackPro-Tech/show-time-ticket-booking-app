package com.showtime.authserver.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.nio.ByteBuffer;

/**
 * @author Vengatesan Nagarajan
 */
@Getter
@Setter
@NoArgsConstructor
@Table(value = "access_token")
public class AccessToken {

    @PrimaryKeyColumn(name = "token_id", type = PrimaryKeyType.PARTITIONED)
    private String tokenId;

    @Column("token")
    private ByteBuffer token;

    @Column("authentication_id")
    private String authenticationId;

    @PrimaryKeyColumn(name = "client_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private String clientId;

    @PrimaryKeyColumn(name = "username", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String username;

    @Column("authentication")
    private ByteBuffer authentication;

    @Column("refresh_token")
    private String refreshToken;

}
