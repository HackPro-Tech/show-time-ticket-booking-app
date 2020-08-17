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
@Table(value = "refresh_token")
public class RefreshToken {

    @PrimaryKey("token_id")
    @CassandraType(type = DataType.Name.TEXT)
    private String tokenId;

    @Column("token")
    @CassandraType(type = DataType.Name.BLOB)
    private ByteBuffer token;

    @Column("authentication")
    @CassandraType(type = DataType.Name.BLOB)
    private ByteBuffer authentication;

}
