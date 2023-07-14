package com.maidev.votingsystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @GetMapping("/maintainChallenges")
    public String maintainChallenges(Model model,Principal principal){

        List<Challenge> allChallenges = challengeService.getAllChallenges();
        allChallenges.stream()
        .filter(challenge -> challenge.getListOfVoters().stream()
        .anyMatch(voter -> voter.getUsername().equals(principal.getName())))
        .forEach(challenge -> challenge.setChecked(true));



        model.addAttribute("challenges", allChallenges);                
        model.addAttribute("loggedUser", principal.getName()); 
        return "maintainChallenges";
    }

     // handler method to handle list of challenges and return model and view        
    @GetMapping("/completedChallenges")
    public String listCompletedChallenges(Model model,Principal principal){

        Map<Boolean, List<Challenge>> challengesByCompletion = getChallengesByCompletion(principal);

        model.addAttribute("completedChallenges", challengesByCompletion.get(true));        
        model.addAttribute("loggedUser", principal.getName()); 
        return "completedChallenges";
    }


    // handler method to handle list of challenges and return model and view        
    @GetMapping("/challenges")
    public String openChallenges(Model model,Principal principal){

        Map<Boolean, List<Challenge>> challengesByCompletion = getChallengesByCompletion(principal);

        model.addAttribute("notcompletedchallenges", challengesByCompletion.get(false));        
        //model.addAttribute("completedchallenges", challengesByCompletion.get(true));        
        model.addAttribute("loggedUser", principal.getName()); 
        return "challenges";
    }


    private Map<Boolean, List<Challenge>> getChallengesByCompletion(Principal principal) {
        List<Challenge> allChallenges = challengeService.getAllChallenges();

        Map<Boolean, List<Challenge>> challengesByCompletion = allChallenges.stream()
        .collect(Collectors.partitioningBy(Challenge::isCompleted));

        challengesByCompletion.get(false).stream()
        .filter(challenge -> challenge.getListOfVoters().stream()
        .anyMatch(voter -> voter.getUsername().equals(principal.getName())))
        .forEach(challenge -> challenge.setChecked(true));

        challengesByCompletion.get(true).stream()
        .filter(challenge -> challenge.getListOfVoters().stream()
        .anyMatch(voter -> voter.getUsername().equals(principal.getName())))
        .forEach(challenge -> challenge.setChecked(true));
        return challengesByCompletion;
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

    @PostMapping("/challenges/{id}")
    public String updateChallenge2(@PathVariable Long id, @ModelAttribute("challenge") Challenge challenge, Model model){
        // get challenge from db by id
        Challenge existsChallenge = getExistsChallenge(id, challenge);
        challengeService.updateChallenge(existsChallenge);
        return "redirect:/challenges";
    }

    private Challenge getExistsChallenge(Long id, Challenge challenge) {
        Challenge existsChallenge = challengeService.getChallengeById(id);
        existsChallenge.setId(id);
        existsChallenge.setTitle(challenge.getTitle());
        existsChallenge.setDescription(challenge.getDescription());
        existsChallenge.setRating(challenge.getRating());
        existsChallenge.setCompleted(challenge.isCompleted());
        return existsChallenge;
    }
    
    // challenge delete handler 
    @GetMapping("/challenges/{id}")
    public String deleteChallenge(@PathVariable Long id){
        challengeService.deleteChallengeById(id);
        return "redirect:/challenges";
    }

    // Vote and Unvote the challenge.
    @GetMapping("/challenges/vote/{id}")
    public String UnVoteChallenge(@PathVariable Long id, Model model,Principal principal){   
        challengeService.voteChallenge(id,principal.getName());
        return "redirect:/challenges";
    }

    // complete the challenge.
    @GetMapping("/challenges/complete/{id}")
    public String completeChallenge(@PathVariable Long id){   
        challengeService.completeChallengeActions(id,true);
        return "redirect:/challenges";
    }

    // uncomplete the challenge.
    @GetMapping("/completedChallenges/uncomplete/{id}")
    public String uncompleteChallenge(@PathVariable Long id){   
        challengeService.completeChallengeActions(id,false);
        return "redirect:/completedChallenges";
    }
}
