package lib.exceptions;

public class NonExistingMembershipFunctionException extends Exception {
    public NonExistingMembershipFunctionException(){
        super("Index of membership function out of range");
    }
}