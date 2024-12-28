package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.scm.Entity.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {
    
    @Autowired
    private UserService userService;
   // private User saveUser;

   @GetMapping("/")
   public String index(){
       return "redirect:/home";
   }

    @GetMapping("/home")
    public String home(Model model)
    {
        //System.out.println("this is home page handler");
        //sending data to view
        model.addAttribute("name", "substring technologies");
        return "home";
    }
    //about rout
    @GetMapping("/about")
    public String aboutPage(){
        //System.out.println("this is about controller");
        return new String("about");
    }
    //service rout
    @GetMapping("/services")
    public String sarvicesPage(){
        //System.out.println("this is services page");
        return new String("services");
    }
    //contact rout
    @GetMapping("/contact")
    public String contactPage(){
       // System.out.println("this is contact page");
        return new String("contact");
    }
    // this is login controller-viewPage
     //login rout
     @GetMapping("/login")
     public String Login(){
        // System.out.println("this is Login page");
         return new String("login");
     } 
     // this is registration controller-viewPage
    //singup/register rout
    @GetMapping("/signup")
    public String Signup(Model model){
        UserForm userForm = new UserForm();
        //send default data
        model.addAttribute("userForm", userForm);
        //userForm.setName("hirdesh");
        //System.out.println("this is signup page");
        return "signup";
    }
    //processing register
    @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegiste(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,HttpSession session){
        //System.out.println("registration process");
        //fetch form data
           // create UserForm class
         //  System.out.println(userForm);
        //validate form data
        if (rBindingResult.hasErrors()) {
            return "signup";
        }
        //save database
        //user service
    //    User user= User.builder()
    //    .name(userForm.getName())
    //    .email(userForm.getEmail())
    //    .password(userForm.getPassword())
    //    .about(userForm.getAbout())
    //    .phoneNumber(userForm.getPhoneNumber())
    //    .profilePic("")
    //    .build();

        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
       // user.setEnabled(true);
        user.setProfilePic("");
        User saveUser = userService.saveUser(user);
       // System.out.println("user saved");
        //massage="Registration Successful"

        //add massage to signup
        Message message= Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        //redirect to signup/register page
        return "redirect:/signup";
    }
}
