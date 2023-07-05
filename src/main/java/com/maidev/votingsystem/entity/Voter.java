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

    public Voter(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column    
    private String username; // email adress of logged user.

}
