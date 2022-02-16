package com.duke.fuzzyapp.lib.outputs;

import com.duke.fuzzyapp.lib.JsonSerializable;
import com.duke.fuzzyapp.lib.exceptions.NonExistingOutputException;

public interface Output extends JsonSerializable {
    public void addOutput();
    public void removeOutput(int index) throws NonExistingOutputException;
    public double getOutput(int index) throws NonExistingOutputException;
    public void evaluate();
    public void calculate();
}
