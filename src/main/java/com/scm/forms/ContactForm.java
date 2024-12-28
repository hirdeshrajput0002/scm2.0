package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {
    
    @NotBlank(message = "Name Is Required")
    private String name;

    @NotBlank(message = "Email Is Requered")
    @Email(message = "Invailid Email Address [ example@gmail.com ]")
    private String email;

    @NotBlank(message = "Phone Number Is Requered")
    @Pattern(regexp = "^[0-9]{10}$",message = "Invailid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Address Is Required")
    private String address;

    //@Column(length = 1000)
    private String discription;
    private boolean favorite = false;
    private String websiteLink;
    private String linkedinLink;
    private MultipartFile contactImage;

}
