package com.maidev.votingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import com.maidev.votingsystem.entity.Challenge;
import com.maidev.votingsystem.service.ChallengeService;




@Controller
public class ChallengeController {  
    @Autowired  
    private  ChallengeService challengeService;

    // handler method to handle list of challenges and return model and view    
    @GetMapping("/ListChallenge")
    public String listChallenges(Model model){
        List<Challenge> allChallenges = challengeService.getAllChallenges();
        System.out.println("Call challenges!");        
        allChallenges.add(new Challenge("Test","Test",false,10));
        model.addAttribute("challenges", allChallenges);        
        return "challenges";
    }
}
