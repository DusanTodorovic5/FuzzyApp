package com.duke.fuzzy_app.membershipFunctions;

import java.util.ArrayList;

public class TriangleMembershipFunction extends MembershipFunction {

    public TriangleMembershipFunction(){
        super(3);
    }
    @Override
    public String getJson() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("\"Type\":\"Triangle\",");
        stringBuilder.append("\"Name\":\"").append(getName()).append("\",");
        stringBuilder.append("\"Parameters\":[");
        for (int i=0;i<2;i++){
            stringBuilder.append("\"").append(parameters[i]).append("\",");
        }
        stringBuilder.append("\"").append(parameters[2]).append("\"");
        stringBuilder.append("]");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public double fuzzify(double number) {
        if (number < parameters[0]){
            return 0.0;
        }
        if (number < parameters[1]){
            return (number - parameters[0])/(parameters[1] - parameters[0]);
        }
        if (number < parameters[2]){
            return (parameters[2] - number)/(parameters[2] - parameters[1]);
        }
        return 0.0;
    }

    @Override
    public double deFuzzify(double number) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double[] applyImplication() {
        // TODO Auto-generated method stub
        return null;
    }
}
