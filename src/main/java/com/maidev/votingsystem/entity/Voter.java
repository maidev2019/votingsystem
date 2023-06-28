package com.maidev.votingsystem.entity;
import jakarta.persistence.*;
@Entity
@Table(name="voter")
public class Voter {

    
    public Voter() {
    }
    

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
