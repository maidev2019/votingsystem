package com.maidev.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.maidev.votingsystem.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long>{

}
