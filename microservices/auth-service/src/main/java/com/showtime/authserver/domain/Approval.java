package com.showtime.authserver.domain;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;

import java.util.Date;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Data
@Table(value = "approval")
public class Approval {

    @PrimaryKey("approval_id")
    @CassandraType(type = DataType.Name.UUID)
    private UUID approvalId;

    @Column("user_id")
    @CassandraType(type = DataType.Name.TEXT)
    private String userId;

    @Column("client_id")
    @CassandraType(type = DataType.Name.TEXT)
    private String clientId;

    @Column("scope")
    @CassandraType(type = DataType.Name.TEXT)
    private String scope;

    @Column("status")
    @CassandraType(type = DataType.Name.TEXT)
    private ApprovalStatus status;

    @Column("expires_at")
    @CassandraType(type = DataType.Name.DATE)
    private Date expiresAt;

    @Column("last_updated_at")
    @CassandraType(type = DataType.Name.DATE)
    private Date lastUpdatedAt;

}
