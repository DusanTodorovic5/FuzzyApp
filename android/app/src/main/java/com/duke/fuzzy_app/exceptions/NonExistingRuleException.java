package com.duke.fuzzy_app.exceptions;

public class NonExistingRuleException extends Exception {
    public NonExistingRuleException(){
        super("Index of rule out of range");
    }
}
