package com.duke.fuzzyapp.lib.exceptions;
public class NonExistingOutputException extends Exception{
    public NonExistingOutputException(){
        super("Index of output out of range");
    }
}
