package com.votaciones.sistema_votaciones.service;

import com.votaciones.sistema_votaciones.dto.CandidateDTO;
import com.votaciones.sistema_votaciones.exception.BusinessException;
import com.votaciones.sistema_votaciones.model.Candidate;
import com.votaciones.sistema_votaciones.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    
    private final CandidateRepository candidateRepository;
    
    // Constructor para inyección de dependencias
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }
    
    @Transactional
    public CandidateDTO registerCandidate(CandidateDTO candidateDTO) {
        if (candidateRepository.existsByName(candidateDTO.getName())) {
            throw new BusinessException("El candidato ya está registrado");
        }
        
        Candidate candidate = new Candidate();
        candidate.setName(candidateDTO.getName());
        candidate.setParty(candidateDTO.getParty());
        candidate.setVotes(0);
        
        Candidate savedCandidate = candidateRepository.save(candidate);
        return convertToDTO(savedCandidate);
    }
    
    public List<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public CandidateDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Candidato no encontrado"));
        return convertToDTO(candidate);
    }
    
    @Transactional
    public void deleteCandidate(Long id) {
        if (!candidateRepository.existsById(id)) {
            throw new BusinessException("Candidato no encontrado");
        }
        candidateRepository.deleteById(id);
    }
    
    private CandidateDTO convertToDTO(Candidate candidate) {
        CandidateDTO dto = new CandidateDTO();
        dto.setId(candidate.getId());
        dto.setName(candidate.getName());
        dto.setParty(candidate.getParty());
        dto.setVotes(candidate.getVotes());
        return dto;
    }
}
