package com.example.distrimo.infra;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Observed
public interface UserJPARepository extends JpaRepository<UserTRecord, UUID> {
    Optional<UserTRecord> findByEmail(String email);
}
