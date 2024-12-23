package com.example.distrimo.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJPARepository extends JpaRepository<UserTRecord, UUID> {
    Optional<UserTRecord> findByEmail(String email);
}
