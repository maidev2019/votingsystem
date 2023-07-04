package com.maidev.votingsystem.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.maidev.votingsystem.entity.AppRole;
import com.maidev.votingsystem.entity.AppUser;
import com.maidev.votingsystem.repository.AppUserRepository;
import com.maidev.votingsystem.service.AppUserService;
import com.maidev.votingsystem.web.dto.AppUserRegistrationDTO;

@Service
public class AppUserServiceImpl implements AppUserService{
    
    private AppUserRepository userRepository;
    public AppUserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;        
    }

    @Override
    public AppUser saveUser(AppUserRegistrationDTO registratotionDTO) {
         AppUser user = new AppUser(registratotionDTO.getFirstName(),
            registratotionDTO.getLastName(),
            registratotionDTO.getEmail(), 
            //new BCryptPasswordEncoder().encode(registratotionDTO.getPassword()), 
            "{noop}"+registratotionDTO.getPassword(), 
            Arrays.asList(new AppRole("ROLE_USER")));    
        return userRepository.save(user);
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(username);
        if(user == null){
            throw new  UsernameNotFoundException("Invalid username or password");
        }
        System.out.println("========|||=========== "+user.toString());
       return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));  
    }
    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<AppRole> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
