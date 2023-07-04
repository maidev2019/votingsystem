package com.maidev.votingsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.maidev.votingsystem.service.AppUserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    @Autowired
    private final AppUserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();        
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());            
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http.authenticationProvider(authenticationProvider())
        // .authenticationManager(new AuthenticationConfiguration().getAuthenticationManager());

        return http.httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests
            (
                auth ->
                    {
                        auth.requestMatchers("/").permitAll();
                        auth.requestMatchers("/registration").permitAll();                    
                        auth.anyRequest().authenticated();    
                    }
            ).formLogin((form) -> 
                    form.loginPage("/login")
                    .permitAll())
            .logout((logout) ->                 
                    logout.invalidateHttpSession(true) 
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                ).build();
        // return http.httpBasic(Customizer.withDefaults()).addFilterAfter(authenticationFilter, BasicAuthenticationFilter.class)
        // .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).build();
    }
}
