package com.votaciones.sistema_votaciones.repository;

import com.votaciones.sistema_votaciones.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByEmail(String email);
    boolean existsByEmail(String email);
}