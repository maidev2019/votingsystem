package com.maidev.votingsystem.entity;

import java.util.ArrayList;
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

    public Challenge(String title, String description, boolean isCompleted, int rating, boolean checked) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.rating = rating;        
        this.listOfVoters = new ArrayList<>();        
        this.checked =checked;
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

    @Column
    private boolean checked;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    name="challengs_votes", 
    joinColumns = @JoinColumn(name="challenge_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name="votes_id", referencedColumnName = "id")
    )
    @Column
    private List<Voter> listOfVoters;


    public void addVoter(Voter voter){
        if(this.listOfVoters == null) {
            this.listOfVoters = new ArrayList<>();
        }
        this.listOfVoters.add(voter);
        setRating(listOfVoters.size());
    }
    public void deleteVoter(Voter voter) {
        if(this.listOfVoters != null && this.listOfVoters.contains(voter)) {
            this.listOfVoters.remove(voter);
            setRating(listOfVoters.size());   
        }        
    }    
}
