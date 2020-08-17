package com.showtime.authserver.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.showtime.authserver.domain.ClientToken;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
@Repository
public interface ClientTokenRepository extends CassandraRepository<ClientToken, String> {
}
