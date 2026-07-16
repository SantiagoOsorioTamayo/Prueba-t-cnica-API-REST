package com.votaciones.sistema_votaciones.repository;

import com.votaciones.sistema_votaciones.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByName(String name);
}