package com.duke.fuzzyapp.lib;

import java.util.ArrayList;
import com.duke.fuzzyapp.lib.methods.*;
import com.duke.fuzzyapp.lib.exceptions.*;
import com.duke.fuzzyapp.lib.outputs.Mamdani;
import com.duke.fuzzyapp.lib.outputs.Output;
public class FuzzySystem implements JsonSerializable{

    public FuzzySystem(){
        this("Enter system name");
    }

    public FuzzySystem(String name) { 
        this.name = name;
        inputs = new ArrayList<>();
        output = new Mamdani();
        rules = new ArrayList<>();
        andMethod = AndMethod.MIN;
        orMethod = OrMethod.MAX;
        implicationMethod = ImplicationMethod.CLIP;
        aggregationMethod = AggregationMethod.MAX;
    }

    public String getName() { 
        return this.name; 
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<FuzzyVariable> getInputs() { 
        return this.inputs; 
    }

    public void addInput(FuzzyVariable fuzzyVariable) {
        this.inputs.add(fuzzyVariable);
    }

    public void removeInput(int index) throws NonExistingInputException {
        try{
            this.inputs.remove(index);
        }
        catch (Exception e){
            throw new NonExistingInputException();
        }
    }

    public FuzzyVariable getInput(int index) throws NonExistingInputException {
        FuzzyVariable returnVariable = null;
        try{
            returnVariable = this.inputs.get(index);
        }
        catch (Exception e){
            throw new NonExistingInputException();
        }
        return returnVariable;
    }

    public Output getOutput() { 
        return this.output; 
    }

    public void setOutputType(Output output){
        this.output = output;
    }

    public void addOutput() { //CHANGE INPUT PARAMETER
        this.output.addOutput();
    }

    public void removeOutput(int index) throws NonExistingOutputException {
        this.output.removeOutput(index);
    }

    public double getOutput(int index) throws NonExistingOutputException { //CHECK RETURN TYPE
        return this.output.getOutput(index);
    }

    public String getType(){
        try{
            return this.output.getClass().getName();
        }
        catch (Exception e){

        }
        return "Mamdani";
    }

    public Double evaluate(Double value){
        //THIS METHOD NEEDS IMPLEMENTATION
        return value;
    }


    public ArrayList<Rule> getRules() { 
        return this.rules; 
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public void removeRule(int index) throws NonExistingRuleException {
        try{
            this.rules.remove(index);
        }
        catch (Exception e){
            throw new NonExistingRuleException();
        }
    }

    public Rule getRule(int index) throws NonExistingRuleException {
        Rule returnRule = null;
        try{
            returnRule = this.rules.get(index);
        }
        catch (Exception e){
            throw new NonExistingRuleException();
        }
        return returnRule;
    }

    public AndMethod getAndMethod() {
        return this.andMethod;
    }

    public void setAndMethod(AndMethod andMethod) {
        this.andMethod = andMethod;
    }

    public OrMethod getOrMethod() {
        return this.orMethod;
    }

    public void setOrMethod(OrMethod orMethod) {
        this.orMethod = orMethod;
    }

    public ImplicationMethod getImplicationMethod() {
        return this.implicationMethod;
    }

    public void setImplicationMethod(ImplicationMethod implicationMethod) {
        this.implicationMethod = implicationMethod;
    }

    public AggregationMethod getAggregationMethod() {
        return this.aggregationMethod;
    }

    public void setAggregationMethod(AggregationMethod aggregationMethod) {
        this.aggregationMethod = aggregationMethod;
    }

    @Override
    public String getJson() {
        // TODO Auto-generated method stub
        return null;
    }
    
    private String name;

    private ArrayList<FuzzyVariable> inputs;

    private Output output;

    private ArrayList<Rule> rules;

    private AndMethod andMethod;

    private OrMethod orMethod;

    private ImplicationMethod implicationMethod;

    private AggregationMethod aggregationMethod;

}
