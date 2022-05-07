package com.duke.fuzzy_app.outputs;

import com.duke.fuzzy_app.JsonSerializable;

public class Function implements JsonSerializable{
    public Function(String name) {
        this.name = name;
        this.value=0.0;
    }
    public Function(String name, double value) {
        this.name = name;
        this.value=value;
    }
    public Function() {
        this.name = "Function name";
        this.value=0.0;
    }

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getJson() {
        StringBuilder stringBuilder = new StringBuilder("{");

        stringBuilder.append("\"Name\" : \"").append(name).append("\",");
        stringBuilder.append("\"Value\" : \"").append(value).append("\"");

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private String name;
    private double value;
    
}
