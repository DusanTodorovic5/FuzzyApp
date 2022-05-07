package com.duke.fuzzy_app.exceptions;

public class NonExistingOutputException extends Exception{
    public NonExistingOutputException(){
        super("Index of output out of range");
    }
}
