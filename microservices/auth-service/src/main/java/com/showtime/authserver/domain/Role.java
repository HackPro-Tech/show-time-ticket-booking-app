package com.showtime.authserver.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Getter
@Setter
@NoArgsConstructor
@Table(value = "role")
public class Role {

    @PrimaryKey("role_id")
    private UUID roleId;

    @Column("role_name")
    private String roleName;
}
