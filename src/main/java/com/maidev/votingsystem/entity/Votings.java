package com.maidev.votingsystem.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="votings")
@Getter
@Setter
public class Votings {

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
