package com.votaciones.sistema_votaciones.dto;

import java.util.List;

public class VoteStatisticsDTO {
    
    private List<CandidateVoteDTO> results;
    private long totalVoters;
    private long totalVotesCast;
    private double participationPercentage;
    
    // Constructor vacío
    public VoteStatisticsDTO() {}
    
    // Constructor con todos los campos
    public VoteStatisticsDTO(List<CandidateVoteDTO> results, long totalVoters, long totalVotesCast, double participationPercentage) {
        this.results = results;
        this.totalVoters = totalVoters;
        this.totalVotesCast = totalVotesCast;
        this.participationPercentage = participationPercentage;
    }
    
    // Getters y Setters
    public List<CandidateVoteDTO> getResults() { return results; }
    public void setResults(List<CandidateVoteDTO> results) { this.results = results; }
    
    public long getTotalVoters() { return totalVoters; }
    public void setTotalVoters(long totalVoters) { this.totalVoters = totalVoters; }
    
    public long getTotalVotesCast() { return totalVotesCast; }
    public void setTotalVotesCast(long totalVotesCast) { this.totalVotesCast = totalVotesCast; }
    
    public double getParticipationPercentage() { return participationPercentage; }
    public void setParticipationPercentage(double participationPercentage) { this.participationPercentage = participationPercentage; }
    
    // Clase interna CandidateVoteDTO
    public static class CandidateVoteDTO {
        private Long candidateId;
        private String candidateName;
        private String party;
        private long votes;
        private double percentage;
        
        // Constructor vacío
        public CandidateVoteDTO() {}
        
        // Constructor con todos los campos
        public CandidateVoteDTO(Long candidateId, String candidateName, String party, long votes, double percentage) {
            this.candidateId = candidateId;
            this.candidateName = candidateName;
            this.party = party;
            this.votes = votes;
            this.percentage = percentage;
        }
        
        // Getters y Setters
        public Long getCandidateId() { return candidateId; }
        public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }
        
        public String getCandidateName() { return candidateName; }
        public void setCandidateName(String candidateName) { this.candidateName = candidateName; }
        
        public String getParty() { return party; }
        public void setParty(String party) { this.party = party; }
        
        public long getVotes() { return votes; }
        public void setVotes(long votes) { this.votes = votes; }
        
        public double getPercentage() { return percentage; }
        public void setPercentage(double percentage) { this.percentage = percentage; }
    }
}