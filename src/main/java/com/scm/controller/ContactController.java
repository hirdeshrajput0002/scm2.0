package com.scm.controller;


import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.Entity.Contact;
import com.scm.Entity.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger=org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    // add contact page:handler
    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
            Authentication authentication, HttpSession session) {
        // process form data
        // validete form
        // add validetion logic

        if (result.hasErrors()) {
            session.setAttribute("message",
                    Message.builder().content("Please currect the following errors").type(MessageType.red).build());
            return "user/add_contact";
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);

        // form----> contact
        User user = userService.getUserByEmail(username);
        // process the contact picture
        // logger.info("file information :{}",contactForm.getContactImage().getOriginalFilename());

         String filename=UUID.randomUUID().toString();
     
        String fileURL=imageService.uploadImage(contactForm.getContactImage(),filename);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDiscription(contactForm.getDiscription());
        contact.setUser(user);
        contact.setLinkedinLink(contactForm.getLinkedinLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);
        contactService.save(contact);
       // System.out.println(contactForm);

        // set the contact picture url
        // set the massage to be on the view
        session.setAttribute("message",
                Message.builder().content("You have successfully added new contact").type(MessageType.green).build());

        return "redirect:/user/contacts/add";
    }

     @RequestMapping
     public String viewContacts(
        @RequestParam(value ="page",defaultValue = "0") int page
        ,@RequestParam(value ="size", defaultValue="10") int size
        ,@RequestParam(value ="sortBy",defaultValue = "name") String sorBy
        ,@RequestParam(value ="direction",defaultValue ="asc") String direction
        ,Model model,Authentication authentication){

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Page<Contact> pageContact = contactService.getByUser(user,page,size,sorBy,direction);
        
        model.addAttribute("pageContact",pageContact);
         return "user/contacts";
     }

    
}
