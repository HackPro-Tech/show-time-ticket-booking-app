package com.showtime.authserver.domain;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Data
@Table(value = "role")
public class Role {

    @PrimaryKey("role_id")
    @CassandraType(type = DataType.Name.UUID)
    private UUID roleId;

    @Column("role_name")
    @CassandraType(type = DataType.Name.TEXT)
    private String roleName;
}
