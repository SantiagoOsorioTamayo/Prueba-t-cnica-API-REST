package com.votaciones.sistema_votaciones.dto;

import jakarta.validation.constraints.NotBlank;

public class CandidateDTO {
    
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    
    private String party;
    
    private Integer votes;
    
    // Constructor vacío
    public CandidateDTO() {}
    
    // Constructor con todos los campos
    public CandidateDTO(Long id, String name, String party, Integer votes) {
        this.id = id;
        this.name = name;
        this.party = party;
        this.votes = votes;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getParty() { return party; }
    public void setParty(String party) { this.party = party; }
    
    public Integer getVotes() { return votes; }
    public void setVotes(Integer votes) { this.votes = votes; }
}