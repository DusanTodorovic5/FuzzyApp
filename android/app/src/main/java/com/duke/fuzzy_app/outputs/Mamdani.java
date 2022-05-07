package com.duke.fuzzy_app.outputs;

import java.util.ArrayList;

import com.duke.fuzzy_app.FuzzyVariable;
import com.duke.fuzzy_app.exceptions.NonExistingOutputException;
import com.duke.fuzzy_app.membershipFunctions.MembershipFunction;

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
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("\"Type\":\"M\",");
        stringBuilder.append("\"Variable\":\"").append(out.getJson()).append("\"");
        stringBuilder.append("}");
        return stringBuilder.toString();
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
