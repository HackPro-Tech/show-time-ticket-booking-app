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
@Table(value = "refresh_token")
public class RefreshToken {

    @PrimaryKey("token_id")
    private String tokenId;

    @Column("token")
    private ByteBuffer token;

    @Column("authentication")
    private ByteBuffer authentication;

    public RefreshToken(String tokenId, ByteBuffer token, ByteBuffer authentication) {
        this.tokenId = tokenId;
        this.token = token;
        this.authentication = authentication;
    }
}
