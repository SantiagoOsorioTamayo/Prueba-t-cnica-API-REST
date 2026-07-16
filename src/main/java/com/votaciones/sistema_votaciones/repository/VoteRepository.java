package com.votaciones.sistema_votaciones.repository;

import com.votaciones.sistema_votaciones.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVoterId(Long voterId);
    long countByCandidateId(Long candidateId);
    
    @Query("SELECT v.candidate.id, COUNT(v) FROM Vote v GROUP BY v.candidate.id")
    List<Object[]> countVotesByCandidate();
}