package com.votaciones.sistema_votaciones.controller;

import com.votaciones.sistema_votaciones.dto.VoterDTO;
import com.votaciones.sistema_votaciones.service.VoterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping
    public ResponseEntity<VoterDTO> registerVoter(@Valid @RequestBody VoterDTO voterDTO) {
        VoterDTO created = voterService.registerVoter(voterDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VoterDTO>> getAllVoters() {
        return ResponseEntity.ok(voterService.getAllVoters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoterDTO> getVoterById(@PathVariable Long id) {
        return ResponseEntity.ok(voterService.getVoterById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoter(@PathVariable Long id) {
        voterService.deleteVoter(id);
        return ResponseEntity.noContent().build();
    }
}