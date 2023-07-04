package com.maidev.votingsystem.web.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class AppUserRegistrationDTO {
    public AppUserRegistrationDTO() {
    }
    
    public AppUserRegistrationDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    private String firstName;    
    private String lastName;    
    private String email;    
    private String password;
}
