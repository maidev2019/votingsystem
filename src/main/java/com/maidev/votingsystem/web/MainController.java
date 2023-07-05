package com.maidev.votingsystem.web;

import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {    
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/")   
    public String home(Principal principal, Model model) {
        model.addAttribute("loggedUser", principal.getName());                
        return "index";
    }
}
