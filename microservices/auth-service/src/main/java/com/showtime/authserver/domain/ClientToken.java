package com.showtime.authserver.domain;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.nio.ByteBuffer;

/**
 * @author Vengatesan Nagarajan
 */
@Data
@Table(value = "client_token")
public class ClientToken {

    @PrimaryKey("token_id")
    @CassandraType(type = DataType.Name.TEXT)
    private String tokenId;

    @Column("token")
    @CassandraType(type = DataType.Name.BLOB)
    private ByteBuffer token;

    @Column("authentication_id")
    @CassandraType(type = DataType.Name.TEXT)
    private String authenticationId;

    @Column("username")
    @CassandraType(type = DataType.Name.TEXT)
    private String username;

    @Column("client_id")
    @CassandraType(type = DataType.Name.TEXT)
    private String clientId;

}
