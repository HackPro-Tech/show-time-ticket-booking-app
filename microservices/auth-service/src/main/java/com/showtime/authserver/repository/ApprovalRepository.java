package com.showtime.authserver.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.showtime.authserver.domain.Approval;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
@Repository
public interface ApprovalRepository extends CassandraRepository<Approval, UUID> {
}
