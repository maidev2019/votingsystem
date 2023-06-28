package com.maidev.votingsystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="challenge")
@Getter
@Setter
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column    
    private String title;

    @Column    
    private String description;

    @Column

    private boolean isCompleted;

    @Column
    private int rating;

    // @ManyToOne
    // @Column
    // private List<Voter> listOfVoters;
    

}
