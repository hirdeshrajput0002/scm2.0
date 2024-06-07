package com.scm.helpers;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String massage){
        super(massage);
    }
    public ResourceNotFoundException(){
        super("Resource Not Found");
    }

}
