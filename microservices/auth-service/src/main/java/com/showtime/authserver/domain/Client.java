package com.showtime.authserver.domain;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.Set;

/**
 * @author Vengatesan Nagarajan
 */
@Data
@Table("client")
public class Client {

    @PrimaryKey("client_id")
    @CassandraType(type = DataType.Name.TEXT)
    private String clientId;

    @Column("client_secret")
    @CassandraType(type = DataType.Name.TEXT)
    private String clientSecret;

    @Column("client_name")
    @CassandraType(type = DataType.Name.TEXT)
    private String clientName;

    @Column("scope")
    @CassandraType(type = DataType.Name.SET, typeArguments = {DataType.Name.TEXT})
    private Set<String> scope;

    @Column("resource_ids")
    @CassandraType(type = DataType.Name.SET, typeArguments = {DataType.Name.TEXT})
    private Set<String> resourceIds;

    @Column("authorized_grant_types")
    @CassandraType(type = DataType.Name.SET, typeArguments = {DataType.Name.TEXT})
    private Set<String> authorizedGrantTypes;

    @Column("registered_redirect_uris")
    @CassandraType(type = DataType.Name.SET, typeArguments = {DataType.Name.TEXT})
    private Set<String> registeredRedirectUris;

    @Column("authorities")
    @CassandraType(type = DataType.Name.LIST, typeArguments = {DataType.Name.TEXT})
    private List<String> authorities;

    @Column("accesstoken_validity_seconds")
    @CassandraType(type = DataType.Name.INT)
    private Integer accessTokenValiditySeconds;

    @Column("refreshtoken_validity_seconds")
    @CassandraType(type = DataType.Name.INT)
    private Integer refreshTokenValiditySeconds;

    @Column("additional_information")
    @CassandraType(type = DataType.Name.TEXT)
    private String additionalInformation;

    @Column("auto_approve_scopes")
    @CassandraType(type = DataType.Name.SET, typeArguments = {DataType.Name.TEXT})
    private Set<String> autoApproveScopes;

}
