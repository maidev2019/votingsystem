package com.maidev.votingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.maidev.votingsystem.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>{
    
}
