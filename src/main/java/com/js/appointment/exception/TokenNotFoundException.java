package com.js.appointment.exception;

public class TokenNotFoundException extends Exception{

    public TokenNotFoundException() {
        super("Token is not created. Please create one");
    }

    public TokenNotFoundException(String message) {
        super("Token is not created. Please create one : Doctor Id" + message);
    }
}
