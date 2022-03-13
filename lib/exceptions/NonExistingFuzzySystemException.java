package lib.exceptions;

public class NonExistingFuzzySystemException extends Exception {
    public NonExistingFuzzySystemException(){
        super("Index of fuzzy system out of range");
    }
}
