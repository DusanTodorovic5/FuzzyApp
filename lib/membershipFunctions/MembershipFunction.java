package lib.membershipFunctions;

import java.util.ArrayList;

import lib.JsonSerializable;

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

    public void addParameters(double parameter, int index) {
        this.parameters[index] = parameter;
    }

    private String name;
    protected double[] parameters;
}
