package com.showtime.userservice.domain;

import com.datastax.driver.core.DataType.Name;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Data
@Table("user_profile")
public class UserProfile {

    @PrimaryKey("user_id")
    @CassandraType(type = Name.UUID)
    private UUID userId;

    @Column("firstname")
    @CassandraType(type = Name.TEXT)
    private String firstname;

    @Column("lastname")
    @CassandraType(type = Name.TEXT)
    private String lastname;

    @Column("date_of_birth")
    @CassandraType(type = Name.DATE)
    private Date dateOfBirth;

    @Column("phone_no")
    @CassandraType(type = Name.TEXT)
    private String phoneNo;

    @Column("email")
    @CassandraType(type = Name.TEXT)
    private String email;

    @Column("whatsapp_notification")
    @CassandraType(type = Name.BOOLEAN)
    private Boolean whatsAppNotification;

    @Column("location")
    @CassandraType(type = Name.TEXT)
    private String location;

    @Column("gender")
    @CassandraType(type = Name.TEXT)
    private String gender;

    @Column("profile_image")
    @CassandraType(type = Name.BLOB)
    private String profileImage;


    // TODO: Auditing needs to do
    @Column("created_at")
    @CassandraType(type = Name.TIMESTAMP)
    private Date createdAt;

    @Column("modified_at")
    @CassandraType(type = Name.TIMESTAMP)
    private Date modifiedAt;
}
