package com.maidev.votingsystem.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="votes")
public class Voter {

    public Voter() {}

    public Voter(String voterName) {
        this.voterName = voterName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column    
    private String voterName;

}
