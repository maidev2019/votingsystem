package com.maidev.votingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maidev.votingsystem.entity.Challenge;
import com.maidev.votingsystem.repository.ChallengeRepository;

@SpringBootApplication
public class VotingsystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VotingsystemApplication.class, args);
	}

	@Autowired
	private ChallengeRepository repository;
	@Override
	public void run(String... args) throws Exception {
		// String description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ";
		// Challenge challenge = new Challenge("Challenge 1", description, false, 10);
		// repository.save(challenge);
		// challenge = new Challenge("Challenge 2", description, false, 10);
		// repository.save(challenge);
		// challenge = new Challenge("Challenge 3", description, false, 10);
		// repository.save(challenge);
	}
}
