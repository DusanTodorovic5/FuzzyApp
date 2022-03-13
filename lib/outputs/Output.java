package lib.outputs;

import lib.JsonSerializable;
import lib.exceptions.NonExistingOutputException;

public interface Output extends JsonSerializable {
    public void addOutput();
    public void removeOutput(int index) throws NonExistingOutputException;
    public double getOutput(int index) throws NonExistingOutputException;
    public void evaluate();
    public void calculate();
    public String getOutputName();
    public double fuzzify(double number,int mfNumber);
    
}
