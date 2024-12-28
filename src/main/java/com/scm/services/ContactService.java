package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scm.Entity.Contact;
import com.scm.Entity.User;

public interface ContactService {

    // save contacts
    Contact save(Contact contact);

    // update contacts
    Contact update(Contact contact);

    // get contacts
    List<Contact> getAll();

    // get contact by id
    Contact getById(String id);

    // delete contacts
    void delete(String id);

     //search contacts
     List<Contact> search(String name, String email, String phoneNumber);

     // get contacts by id
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user,int page, int size,String sortField,String sortDirection);


}
