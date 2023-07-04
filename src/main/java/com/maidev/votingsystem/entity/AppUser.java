package com.maidev.votingsystem.entity;

import java.util.Collection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="appuser", uniqueConstraints = @UniqueConstraint(columnNames= {"email"}))
@Getter
@Setter
@ToString
public class AppUser {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    
    private String email;
    
    private String password;

    private boolean enabled;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name="users_roles", 
        joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
    )
    private Collection<AppRole> roles;

    public AppUser (){

    }

    public AppUser(String firstName, String lastName, String email, String password, Collection<AppRole> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    
}
