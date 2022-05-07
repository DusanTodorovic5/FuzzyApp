package com.duke.fuzzy_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import com.duke.fuzzy_app.exceptions.NonExistingFuzzySystemException;
import com.duke.fuzzy_app.membershipFunctions.MembershipFunction;
import com.duke.fuzzy_app.membershipFunctions.TrapezoidMembershipFunction;
import com.duke.fuzzy_app.membershipFunctions.TriangleMembershipFunction;
import com.duke.fuzzy_app.methods.AggregationMethod;
import com.duke.fuzzy_app.methods.AndMethod;
import com.duke.fuzzy_app.methods.ConnectionMethod;
import com.duke.fuzzy_app.methods.OrMethod;
import com.duke.fuzzy_app.outputs.Function;
import com.duke.fuzzy_app.outputs.Mamdani;
import com.duke.fuzzy_app.outputs.Output;
import com.duke.fuzzy_app.outputs.Sugeno;

import org.json.*;

public class FuzzyLibraryController {
    /*
     * This method is a bit weird maybe. Rule class has map with refrences, so instead of json keeping id of
     * refrenced object, it stores whole object again, but during parsing, i map the json class with refrence of generated 
     * object so once it gets to rule, instead of creating new object from json it finds it in map and sends a refrences
    */
    public void load(String json){
        try{
            fuzzySystems.clear();
            JSONArray rootObj = new JSONArray(json);

            for (int i=0;i<rootObj.length();i++){
                JSONObject system = rootObj.getJSONObject(i);
                FuzzySystem fs = new FuzzySystem(system.getString("Name"));
                fs.setAndMethod(AndMethod.valueOf(system.getString("And Method")));
                fs.setAggregationMethod(AggregationMethod.valueOf(system.getString("Implication Method")));
                fs.setOrMethod(OrMethod.valueOf(system.getString("Or Method")));

                HashMap<String, FuzzyVariable> inputMap = new HashMap<String, FuzzyVariable>();

                JSONArray inputs = system.getJSONArray("Input");

                for (int j=0;j<inputs.length();j++){
                    FuzzyVariable fv = FuzzyVariable.parseJson(inputs.getJSONObject(j).toString());

                    inputMap.put(inputs.getJSONObject(j).toString(), fv);

                    fs.addInput(fv);
                }

                JSONArray outputs = system.getJSONArray("Output");
                HashMap<String, Output> outputMap = new HashMap<String, Output>();
                for (int j=0;j<outputs.length();j++){
                    JSONObject output = outputs.getJSONObject(j);
                    Output op = Output.parseJSON(output.toString());

                    outputMap.put(output.toString(),op);

                    fs.addOutput(op);
                }
                JSONArray rules = system.getJSONArray("Rule");

                for (int j=0;j<rules.length();j++){
                    JSONObject rule = rules.getJSONObject(j);
                    Rule r = new Rule();
                    r.setConnectionMethod(ConnectionMethod.valueOf(rule.getString("Connection Method")));
                    JSONArray ruleInputs = rule.getJSONArray("Inputs");
                    for (int k=0;k<ruleInputs.length();k++){
                        JSONObject ruleInput = ruleInputs.getJSONObject(k);
                        r.addInput(new Triple<FuzzyVariable, Integer, Boolean>(
                                inputMap.get(ruleInput.getJSONObject("FuzzyVariable").toString()),
                                Integer.parseInt(ruleInput.getString("Integer")),
                                Boolean.parseBoolean(ruleInput.getString("Boolean"))
                        ));
                    }
                    JSONArray ruleOutputs = rule.getJSONArray("Outputs");
                    for (int k=0;k<ruleOutputs.length();k++){
                        JSONObject ruleOutput = ruleOutputs.getJSONObject(k);
                        r.addOutput(new Triple<Output, Integer, Boolean>(
                                outputMap.get(ruleOutput.getJSONObject("Output").toString()),
                                Integer.parseInt(ruleOutput.getString("Integer")),
                                Boolean.parseBoolean(ruleOutput.getString("Boolean"))
                        ));
                    }

                    fs.addRule(r);
                }

                fuzzySystems.add(fs);
            }
        } catch (Exception e){

        }
    }   
    /*
     *  This method will be long, as it will constantly call getJson() method from JsonSerializable interface.
     *  That means that a lot of strings will be created, as Java uses garbage collector, the whole process will
     *  be very memory hungry so instead of using concat I use stringBuilder to clean memory for me every time
     *  I wish to concate. That way i will save a lot of memory, especially for worse hardware devices like phones,
     *  which this library is intended for.
    */
    public String getJson(){
        StringBuilder stringBuilder = new StringBuilder("[");
        //Not using iterator here, it's simple array and I need to know the last element, faster and easier to just find last i
        if (fuzzySystems.size() > 0){
            for (int i=0;i<fuzzySystems.size() - 1;i++){
                stringBuilder.append(fuzzySystems.get(i).getJson()).append(",");
            }
            //adding last input from array, will not cause exception as input.size() must be greater than 0
            stringBuilder.append(fuzzySystems.get(fuzzySystems.size() - 1).getJson());
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
    
    public void addFuzzySystem() {
        fuzzySystems.add(new FuzzySystem());
    }

    public FuzzySystem editFuzzySystem(int index) throws NonExistingFuzzySystemException {
        FuzzySystem returnSystem = null;
        try{
            returnSystem = fuzzySystems.get(index);
        }
        catch(Exception e){
            throw new NonExistingFuzzySystemException();
        }
        return returnSystem;
    }

    public void removeFuzzySystem(int index) throws NonExistingFuzzySystemException {
        try{
            fuzzySystems.remove(index);
        }
        catch(Exception e){
            throw new NonExistingFuzzySystemException();
        }
    }
    //SREDI LEPO
    public static String[] getOutputs(){
        String[] returnType = {"Mamdani", "Sugeno"};
            return returnType;
    }
    //SREDI LEPO
    public static String[] getMembershipFunctions(){
        String [] returnType = {"Trapezoid", "Triangle"};
        return returnType;
    }

    public static FuzzyLibraryController getInstance(){
        if (instance == null){
            instance = new FuzzyLibraryController();
        }
        return instance;
    }

    public ArrayList<String> getFuzzySystems(){
        ArrayList<String> systems = new ArrayList();
        for (FuzzySystem fs : fuzzySystems){
            systems.add(fs.getName());
        }
        return systems;
    }

    private FuzzyLibraryController(){
    	fuzzySystems = new ArrayList<FuzzySystem>();
    }

    private ArrayList<FuzzySystem> fuzzySystems;
    private static FuzzyLibraryController instance = null;
}
