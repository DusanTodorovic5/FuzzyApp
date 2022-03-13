package lib.outputs;

import java.util.ArrayList;

import lib.FuzzyVariable;
import lib.exceptions.NonExistingOutputException;
import lib.membershipFunctions.MembershipFunction;

public class Mamdani implements Output {

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
        // TODO Auto-generated method stub
        return 0;
    }
    
    public void addMembershipFunction(MembershipFunction mf) {
    	out.addMembershipFunction(mf);
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
        return this.out.getName();
    }
    @Override
    public double fuzzify(double number,int mfNumber){
        return out.fuzzify(number, mfNumber);
    }

    public double[] linspace(int points) {  
        double[] d = new double[points];  
        for (int i = 0; i < points; i++){  
            d[i] = this.out.getStart() + ((double)i * (this.out.getEnd() - this.out.getStart()) / ((double)points - 1));  
        }  
        return d;  
    }
    
    public Mamdani() {
    	this("Enter output name");
    }
    
    public Mamdani(String name){
    	super();
    	out = new FuzzyVariable(name);
    }
    
    public FuzzyVariable getVariable() {
    	return this.out;
    }
    
    private FuzzyVariable out;

    
}
