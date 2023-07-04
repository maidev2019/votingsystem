package com.maidev.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.maidev.votingsystem.entity.AppUser;

//@Transactional(readOnly = true)
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
   AppUser  findByEmail(String email);
    
}
