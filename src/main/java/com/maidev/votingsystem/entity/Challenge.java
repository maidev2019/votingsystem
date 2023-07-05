package com.maidev.votingsystem.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="challenge")
public class Challenge {

    public Challenge(){}

    public Challenge(String title, String description, boolean isCompleted, int rating) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.rating = rating;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(length = 2048)
    private String description;

    @Column
    private boolean isCompleted;

    @Column
    private int rating;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    name="challengs_votes", 
    joinColumns = @JoinColumn(name="challenge_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name="votes_id", referencedColumnName = "id")
    )
    @Column
    private List<Voter> listOfVoters;

}
