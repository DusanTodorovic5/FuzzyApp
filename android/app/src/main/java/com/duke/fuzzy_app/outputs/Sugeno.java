package com.duke.fuzzy_app.outputs;


import java.util.ArrayList;

import com.duke.fuzzy_app.Pair;
import com.duke.fuzzy_app.exceptions.NonExistingOutputException;

public class Sugeno implements Output {
    public Sugeno(String name){
        super();
        this.name=name;
        this.out = new ArrayList<Function>();
    }

    public void addFunction(Function f) {
        out.add(f);
        
    }

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
        return out.get(index).getValue();
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
        stringBuilder.append("\"Type\":\"S\",");
        stringBuilder.append("\"Name\":\"").append(name).append("\",");
        stringBuilder.append("\"Functions\":[");
        if (out.size() > 0){
            for (int i=0;i<out.size()-1;i++){
                stringBuilder.append(out.get(i).getJson()).append(",");
            }
            stringBuilder.append(out.get(out.size()-1).getJson());
        }
        stringBuilder.append("]");
        stringBuilder.append("}");
        return stringBuilder.toString();
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

    public Function getFunction(int index){
        return out.get(index);
    }

    private String name;
    private ArrayList<Function> out;
	
}