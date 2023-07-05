package com.maidev.votingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.maidev.votingsystem.entity.Challenge;
import com.maidev.votingsystem.service.ChallengeService;




@Controller
public class ChallengeController {  
    @Autowired  
    private  ChallengeService challengeService;

    // handler method to handle list of challenges and return model and view        
    @GetMapping("/challenges")
    public String listChallenges(Model model){
        List<Challenge> allChallenges = challengeService.getAllChallenges();        
        model.addAttribute("challenges", allChallenges);        
        return "challenges";
    }
    @GetMapping("/challenges/new")
    public String createChallenge(Model model){
        Challenge challenge = new Challenge();
        model.addAttribute("challenge", challenge);
        return "create_challenge";
    }
    @PostMapping("/challenges")
    public String saveChallenge(@ModelAttribute("challenge") Challenge challenge){
        challengeService.saveChallenge(challenge);
        return "redirect:/challenges";
    }

    @GetMapping("/challenges/edit/{id}")
    public String updateChallengeForm(@PathVariable Long id, Model model){
        Challenge challenge = challengeService.getChallengeById(id);
        model.addAttribute("challenge", challenge);
        return "update_challenge";
    }
    
    @PostMapping("/challenges/vote/{id}")
    public String voteChallenge(){
        System.out.println("we are in vote challenge method");
        return "";
    }
    @PostMapping("/challenges/{id}")
    public String updateChallenge2(@PathVariable Long id, @ModelAttribute("challenge") Challenge challenge, Model model){
        // get challenge from db by id
        Challenge existsChallenge = challengeService.getChallengeById(id);
        existsChallenge.setId(id);
        existsChallenge.setTitle(challenge.getTitle());
        existsChallenge.setDescription(challenge.getDescription());
        existsChallenge.setRating(challenge.getRating());
        existsChallenge.setCompleted(challenge.isCompleted());
        challengeService.updateChallenge(existsChallenge);

        return "redirect:/challenges";
    }
    
    // challenge delete handler 
    @GetMapping("/challenges/{id}")
    public String deleteChallenge(@PathVariable Long id){
        challengeService.deleteChallengeById(id);
        return "redirect:/challenges";
    }
}
