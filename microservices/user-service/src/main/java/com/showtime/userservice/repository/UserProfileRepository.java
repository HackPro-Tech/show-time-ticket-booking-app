package com.showtime.userservice.repository;

import com.showtime.userservice.domain.UserProfile;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Vengatesan Nagarajan
 */
@Repository
public interface UserProfileRepository extends CassandraRepository<UserProfile, UUID> {

    Optional<UserProfile> findByUserId(UUID userId);

    boolean deleteByUserId(UUID userId);

}
