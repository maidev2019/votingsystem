package com.maidev.votingsystem.entity;


import jakarta.persistence.*;
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

    @Column    
    private String title;

    @Column    
    private String description;

    @Column

    private boolean isCompleted;

    @Column
    private int rating;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // @ManyToOne
    // @Column
    // private List<Voter> listOfVoters;
    

}
