package com.duke.fuzzy_app.membershipFunctions;

import java.util.ArrayList;

import com.duke.fuzzy_app.JsonSerializable;

public abstract class MembershipFunction implements JsonSerializable{
    
    public abstract double fuzzify(double number);

    public abstract double deFuzzify(double number);

    public abstract double[] applyImplication();

    protected MembershipFunction(int size){
        parameters = new double[size];
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getParameters() {
        return this.parameters;
    }

    public void addParameters(double parameter, int index) { //TODO: implement valid check
        this.parameters[index] = parameter;
    }

    private String name;
    protected double[] parameters;
}
