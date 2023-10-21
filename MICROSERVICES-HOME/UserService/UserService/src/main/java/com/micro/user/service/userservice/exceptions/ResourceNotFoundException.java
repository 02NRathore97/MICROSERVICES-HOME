package com.micro.user.service.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    //default constructor
    public ResourceNotFoundException(){
        super("Resource not found on server !!");
    }

    //parameterized constructor
    public ResourceNotFoundException(String message){
        super(message);
    }
}
