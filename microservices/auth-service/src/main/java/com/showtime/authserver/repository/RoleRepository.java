package com.showtime.authserver.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.showtime.authserver.domain.Role;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
@Repository
public interface RoleRepository extends CassandraRepository<Role, UUID> {

	Role findByRoleName(String roleName);

}
