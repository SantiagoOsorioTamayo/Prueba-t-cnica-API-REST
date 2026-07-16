package com.votaciones.sistema_votaciones.controller;

import com.votaciones.sistema_votaciones.dto.VoteStatisticsDTO;
import com.votaciones.sistema_votaciones.model.Vote;
import com.votaciones.sistema_votaciones.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Void> castVote(
            @RequestParam Long voterId,
            @RequestParam Long candidateId) {
        voteService.castVote(voterId, candidateId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes() {
        return ResponseEntity.ok(voteService.getAllVotes());
    }

    @GetMapping("/statistics")
    public ResponseEntity<VoteStatisticsDTO> getStatistics() {
        return ResponseEntity.ok(voteService.getStatistics());
    }
}