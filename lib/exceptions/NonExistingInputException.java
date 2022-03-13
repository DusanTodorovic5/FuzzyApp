package lib.exceptions;

public class NonExistingInputException extends Exception {
    public NonExistingInputException(){
        super("Index of input out of range");
    }
}
