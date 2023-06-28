package com.maidev.votingsystem.entity;
import jakarta.persistence.*;
@Entity
@Table(name="votings")
public class Votings {

    public Votings(){}
    
    public Votings(Long voterId, Long challengeId, boolean isVoted) {
        this.voterId = voterId;
        this.challengeId = challengeId;
        this.isVoted = isVoted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long voterId;

    @Column
    private Long challengeId;

    @Column
    private boolean isVoted;

    
}
