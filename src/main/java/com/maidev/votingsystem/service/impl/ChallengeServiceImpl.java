package com.maidev.votingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maidev.votingsystem.entity.Challenge;
import com.maidev.votingsystem.repository.ChallengeRepository;
import com.maidev.votingsystem.service.ChallengeService;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService{ 
    @Autowired  
    private ChallengeRepository challengeRepository;

    @Override
    public List<Challenge> getAllChallenges() {
        List<Challenge> findAll = challengeRepository.findAll();
        for (Challenge challenge : findAll) {
            System.out.println(challenge.getTitle() + " - "+ challenge.getListOfVoters());
        }
        return challengeRepository.findAll();
    }

    @Override
    public Challenge saveChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public Challenge getChallengeById(Long id) {
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
}
