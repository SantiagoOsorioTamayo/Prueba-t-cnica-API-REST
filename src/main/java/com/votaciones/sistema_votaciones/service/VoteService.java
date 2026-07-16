package com.votaciones.sistema_votaciones.service;

import com.votaciones.sistema_votaciones.dto.VoteStatisticsDTO;
import com.votaciones.sistema_votaciones.exception.BusinessException;
import com.votaciones.sistema_votaciones.model.Candidate;
import com.votaciones.sistema_votaciones.model.Vote;
import com.votaciones.sistema_votaciones.model.Voter;
import com.votaciones.sistema_votaciones.repository.CandidateRepository;
import com.votaciones.sistema_votaciones.repository.VoteRepository;
import com.votaciones.sistema_votaciones.repository.VoterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteService {
    
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;
    
    // Constructor para inyección de dependencias (reemplaza @RequiredArgsConstructor)
    public VoteService(VoteRepository voteRepository, VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }
    
    @Transactional
    public void castVote(Long voterId, Long candidateId) {
        Voter voter = voterRepository.findById(voterId)
            .orElseThrow(() -> new BusinessException("Votante no encontrado"));
        
        if (voter.getHasVoted()) {
            throw new BusinessException("El votante ya ha emitido su voto");
        }
        
        Candidate candidate = candidateRepository.findById(candidateId)
            .orElseThrow(() -> new BusinessException("Candidato no encontrado"));
        
        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);
        
        candidate.setVotes(candidate.getVotes() + 1);
        candidateRepository.save(candidate);
        
        voter.setHasVoted(true);
        voterRepository.save(voter);
        
        voteRepository.save(vote);
    }
    
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }
    
    public VoteStatisticsDTO getStatistics() {
        long totalVoters = voterRepository.count();
        long totalVotesCast = voteRepository.count();
        double participationPercentage = totalVoters > 0 ? 
            (double) totalVotesCast / totalVoters * 100 : 0;
        
        List<Object[]> votesByCandidate = voteRepository.countVotesByCandidate();
        List<VoteStatisticsDTO.CandidateVoteDTO> results = new ArrayList<>();
        
        for (Object[] result : votesByCandidate) {
            Long candidateId = (Long) result[0];
            Long voteCount = (Long) result[1];
            
            Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
            if (candidate != null) {
                double percentage = totalVotesCast > 0 ? 
                    (double) voteCount / totalVotesCast * 100 : 0;
                
                results.add(new VoteStatisticsDTO.CandidateVoteDTO(
                    candidateId,
                    candidate.getName(),
                    candidate.getParty(),
                    voteCount,
                    percentage
                ));
            }
        }
        
        return new VoteStatisticsDTO(results, totalVoters, totalVotesCast, participationPercentage);
    }
}