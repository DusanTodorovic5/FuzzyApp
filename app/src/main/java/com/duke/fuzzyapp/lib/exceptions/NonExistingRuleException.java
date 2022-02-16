package com.duke.fuzzyapp.lib.exceptions;

public class NonExistingRuleException extends Exception {
    public NonExistingRuleException(){
        super("Index of rule out of range");
    }
}
