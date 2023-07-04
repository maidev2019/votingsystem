package com.maidev.votingsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.maidev.votingsystem.entity.AppUser;
import com.maidev.votingsystem.web.dto.AppUserRegistrationDTO;

public interface AppUserService extends UserDetailsService{
    AppUser saveUser(AppUserRegistrationDTO registratotionDTO);

}
