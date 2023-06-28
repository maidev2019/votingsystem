package com.maidev.votingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maidev.votingsystem.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ChallengeController {
    @Autowired
    private final ChallengeService challengeService;

    // handler method to handle list of challenges and return model and view
    @GetMapping("/challenges")
    public String listChallenges(Model model){
        model.addAttribute("challenges", challengeService.getAllChallenges());
        return "challenges";
    }
}
