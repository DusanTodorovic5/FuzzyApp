package com.duke.fuzzyapp.lib.membershipFunctions;

import java.util.ArrayList;

import com.duke.fuzzyapp.lib.JsonSerializable;

public abstract class MembershipFunction implements JsonSerializable{
    
    
    public abstract double fuzzify(double number);

    public abstract double deFuzzify(double number);

    public abstract void plot();

    public abstract ArrayList<Double> applyImplication();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getParameters() {
        return this.parameters;
    }

    public void addParameters(double parameter) {
        this.parameters.add(parameter);
    }

    private String name;
    private ArrayList<Double> parameters;
}
