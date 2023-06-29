package com.maidev.votingsystem.service;

import java.util.List;

import com.maidev.votingsystem.entity.Challenge;

public interface ChallengeService { 
    
    public List<Challenge> getAllChallenges();

    Challenge saveChallenge(Challenge challenge);

    Challenge getChallengeById(Long id);

    Challenge updateChallenge(Challenge challenge);

    void deleteChallengeById(Long id);
}
