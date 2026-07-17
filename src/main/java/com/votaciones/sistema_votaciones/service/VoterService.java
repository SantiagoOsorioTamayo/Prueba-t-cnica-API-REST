package com.votaciones.sistema_votaciones.service;

import com.votaciones.sistema_votaciones.dto.VoterDTO;
import com.votaciones.sistema_votaciones.exception.BusinessException;
import com.votaciones.sistema_votaciones.model.Voter;
import com.votaciones.sistema_votaciones.repository.VoterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoterService {
    
    private final VoterRepository voterRepository;
    
    // Constructor para inyección de dependencias
    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }
    
    @Transactional
    public VoterDTO registerVoter(VoterDTO voterDTO) {
        if (voterRepository.existsByEmail(voterDTO.getEmail())) {
            throw new BusinessException("El email ya está registrado");
        }
        
        Voter voter = new Voter();
        voter.setName(voterDTO.getName());
        voter.setEmail(voterDTO.getEmail());
        voter.setHasVoted(false);
        
        Voter savedVoter = voterRepository.save(voter);
        return convertToDTO(savedVoter);
    }
    
    public List<VoterDTO> getAllVoters() {
        return voterRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public VoterDTO getVoterById(Long id) {
        Voter voter = voterRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Votante no encontrado"));
        return convertToDTO(voter);
    }
    
    @Transactional
    public void deleteVoter(Long id) {
        if (!voterRepository.existsById(id)) {
            throw new BusinessException("Votante no encontrado");
        }
        voterRepository.deleteById(id);
    }
    
    private VoterDTO convertToDTO(Voter voter) {
        VoterDTO dto = new VoterDTO();
        dto.setId(voter.getId());
        dto.setName(voter.getName());
        dto.setEmail(voter.getEmail());
        dto.setHasVoted(voter.getHasVoted());
        return dto;
    }
}
