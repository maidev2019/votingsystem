package com.maidev.votingsystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maidev.votingsystem.service.AppUserService;
import com.maidev.votingsystem.web.dto.AppUserRegistrationDTO;

@Controller
@RequestMapping("/registration")
public class AppUserRegistrationController{

    private AppUserService userService;

    public AppUserRegistrationController(AppUserService userService) {
        this.userService = userService;
    }
    // need this to reference it in registration.html form. See alternative below
    @ModelAttribute("user")
    public AppUserRegistrationDTO appUserRegistrationDTO(){
        return new AppUserRegistrationDTO();
    }
    @GetMapping
    public String showRegistrationForm(){
        // Alternative: pass parameter Model model
        // add here: model.addModelAttribute("user", new AppUserRegistrationDTO())
        return "registration";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") AppUserRegistrationDTO registratotionDTO){
        userService.saveUser(registratotionDTO);
        return "redirect:/registration?success";

    }
}