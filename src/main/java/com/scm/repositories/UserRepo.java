package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User , String>{
    //database related operations

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    

}
