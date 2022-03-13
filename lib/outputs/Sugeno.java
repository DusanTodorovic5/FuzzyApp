package lib.outputs;


import java.util.ArrayList;

import lib.Pair;
import lib.exceptions.NonExistingOutputException;

public class Sugeno implements Output {
    @Override
    public void addOutput() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeOutput(int index) throws NonExistingOutputException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getOutput(int index) throws NonExistingOutputException {
        return out.get(index).getSecond();
    }

    @Override
    public void evaluate() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getJson() {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public String getOutputName(){
        return this.name;
    }

    @Override
	public double fuzzify(double number, int mfNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

    private String name;
    private ArrayList<Pair<String,Double>> out;
	
}
