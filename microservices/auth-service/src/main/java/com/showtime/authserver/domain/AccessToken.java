package com.showtime.authserver.domain;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.nio.ByteBuffer;

/**
 * @author Vengatesan Nagarajan
 */
@Data
@Table(value = "access_token")
public class AccessToken {

    @PrimaryKeyColumn(name = "token_id", type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = DataType.Name.TEXT)
    private String tokenId;

    @Column("token")
    @CassandraType(type = DataType.Name.BLOB)
    private ByteBuffer token;

    @Column("authentication_id")
    @CassandraType(type = DataType.Name.TEXT)
    private String authenticationId;

    @PrimaryKeyColumn(name = "client_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = DataType.Name.TEXT)
    private String clientId;

    @PrimaryKeyColumn(name = "username", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = DataType.Name.TEXT)
    private String username;

    @Column("authentication")
    @CassandraType(type = DataType.Name.BLOB)
    private ByteBuffer authentication;

    @Column("refresh_token")
    @CassandraType(type = DataType.Name.TEXT)
    private String refreshToken;

}
