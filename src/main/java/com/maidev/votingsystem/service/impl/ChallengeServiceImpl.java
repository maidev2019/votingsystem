package com.maidev.votingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maidev.votingsystem.entity.Challenge;
import com.maidev.votingsystem.repository.ChallengeRepository;
import com.maidev.votingsystem.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService{ 
    @Autowired  
    private final ChallengeRepository challengeRepository;

    @Override
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }
}
