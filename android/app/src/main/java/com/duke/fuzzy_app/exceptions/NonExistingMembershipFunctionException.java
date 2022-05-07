package com.duke.fuzzy_app.exceptions;

public class NonExistingMembershipFunctionException extends Exception {
    public NonExistingMembershipFunctionException(){
        super("Index of membership function out of range");
    }
}