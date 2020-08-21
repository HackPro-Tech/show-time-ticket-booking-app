package com.showtime.authserver.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.nio.ByteBuffer;

/**
 * @author Vengatesan Nagarajan
 */
@Getter
@Setter
@NoArgsConstructor
@Table(value = "client_token")
public class ClientToken {

    @PrimaryKey("token_id")
    private String tokenId;

    @Column("token")
    private ByteBuffer token;

    @Column("authentication_id")
    private String authenticationId;

    @Column("username")
    private String username;

    @Column("client_id")
    private String clientId;

}
