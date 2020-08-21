package com.showtime.authserver.repository;

import com.showtime.authserver.domain.RefreshToken;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Vengatesan Nagarajan
 */
@Repository
public interface RefreshTokenRepository extends CassandraRepository<RefreshToken, String> {
    Optional<RefreshToken> findByTokenId(String tokenId);

}
