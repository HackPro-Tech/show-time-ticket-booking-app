package com.showtime.authserver.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.showtime.authserver.domain.Client;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
@Repository
public interface ClientRepository extends CassandraRepository<Client, String> {

	Optional<Client> findByClientId(String clientId);

}
