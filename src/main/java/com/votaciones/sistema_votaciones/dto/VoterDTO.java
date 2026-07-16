package com.votaciones.sistema_votaciones.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class VoterDTO {
    
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email debe ser válido")
    private String email;
    
    private Boolean hasVoted;
    
    // Constructor vacío
    public VoterDTO() {}
    
    // Constructor con todos los campos
    public VoterDTO(Long id, String name, String email, Boolean hasVoted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hasVoted = hasVoted;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Boolean getHasVoted() { return hasVoted; }
    public void setHasVoted(Boolean hasVoted) { this.hasVoted = hasVoted; }
}