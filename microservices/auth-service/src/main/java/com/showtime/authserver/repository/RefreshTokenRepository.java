package com.showtime.authserver.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.showtime.authserver.domain.RefreshToken;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
@Repository
public interface RefreshTokenRepository extends CassandraRepository<RefreshToken, String> {

	@Query("select * from refresh_token where token_id = ?0")
	RefreshToken findByTokenId(String tokenId);

}
