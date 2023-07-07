package com.maidev.votingsystem.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import com.maidev.votingsystem.entity.Challenge;
import com.maidev.votingsystem.entity.Voter;

import com.maidev.votingsystem.repository.ChallengeRepository;
import com.maidev.votingsystem.repository.VoterRepository;
import com.maidev.votingsystem.service.ChallengeService;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService{ 
    @Autowired      
    private ChallengeRepository challengeRepository;

    @Autowired  
    private VoterRepository voterRepository;
    private Sort sortByRating = Sort.by("rating").descending();

    @Override
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll(sortByRating);
    }

    @Override
    public Challenge saveChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public Challenge getChallengeById(Long id) {
       return findChallengeById(id);
    }

    private Challenge findChallengeById(Long id) {
        return challengeRepository.findById(id).get();
    }

    @Override
    public Challenge updateChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public void deleteChallengeById(Long id) {
        challengeRepository.deleteById(id);
    }

    @Override
    public void voteChallenge(Long id, String username) {        
        
        Challenge findById = findChallengeById(id);
        Voter findVoterByUserName = findById.getListOfVoters()
        .stream()
        .filter(voter -> voter.getUsername().equals(username))
        .findFirst()
        .orElse(null);
       
        if(findVoterByUserName != null){ // unvote
            findById.deleteVoter(findVoterByUserName);
            voterRepository.delete(findVoterByUserName);
            challengeRepository.save(findById);
        }else{ // vote           
            Voter voter = new Voter(username);    
            voterRepository.save(voter);
            findById.addVoter(voter);
            challengeRepository.save(findById);
        }
    }
   @Override
    public void completeChallengeActions(Long id, boolean isCompleted) {
        Challenge foundChallenge = findChallengeById(id);
        if(foundChallenge == null) return;
        foundChallenge.setCompleted(isCompleted);
        challengeRepository.save(foundChallenge);
    }
}