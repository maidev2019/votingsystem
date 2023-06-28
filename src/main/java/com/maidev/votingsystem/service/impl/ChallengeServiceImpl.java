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

    // public ChallengeServiceImpl(ChallengeRepository challengeRepository) {
    //     super();
    //     this.challengeRepository = challengeRepository;
    // }

    @Override
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }
}
