package com.votaciones.sistema_votaciones.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {
    @UniqueConstraint(columnNames = "voter_id")
})
public class Vote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "voter_id", nullable = false, unique = true)
    private Voter voter;
    
    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;
    
    @Column(name = "voted_at")
    private LocalDateTime votedAt;
    
    // Constructor vacío
    public Vote() {}
    
    // Constructor con todos los campos
    public Vote(Long id, Voter voter, Candidate candidate, LocalDateTime votedAt) {
        this.id = id;
        this.voter = voter;
        this.candidate = candidate;
        this.votedAt = votedAt;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Voter getVoter() { return voter; }
    public void setVoter(Voter voter) { this.voter = voter; }
    
    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }
    
    public LocalDateTime getVotedAt() { return votedAt; }
    public void setVotedAt(LocalDateTime votedAt) { this.votedAt = votedAt; }
    
    @PrePersist
    protected void onCreate() {
        votedAt = LocalDateTime.now();
    }
}