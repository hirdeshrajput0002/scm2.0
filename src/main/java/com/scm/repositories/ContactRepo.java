package com.scm.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.Entity.Contact;
import com.scm.Entity.User;

@Repository
public interface ContactRepo extends JpaRepository< Contact, String> {
       //find the contact by user
       //custom finder method
       Page<Contact> findByUser(User user,Pageable pageable);
       
       //custom query method to get all contacts of a user
       @Query("SELECT c FROM Contact c WHERE c.user.id=:userId")
       List<Contact> findByUserId(@Param("userId") String userId);
}
