package com.duke.fuzzy_app;

import java.util.ArrayList;
import java.util.HashMap;

import com.duke.fuzzy_app.methods.ConnectionMethod;
import com.duke.fuzzy_app.outputs.Output;

public class Rule implements JsonSerializable{


    public ConnectionMethod getConnectionMethod() {
        return this.connectionMethod;
    }

    public void setConnectionMethod(ConnectionMethod connectionMethod) {
        this.connectionMethod = connectionMethod;
    }

    

    public Triple<FuzzyVariable,Integer,Boolean> getInput(String key){
        return inputs.get(key);
    }

    public HashMap<String, Triple<FuzzyVariable, Integer, Boolean>> getInputs(){return this.inputs;}
    
    public Triple<Output, Integer, Boolean> getOutput(String key){
        return outputs.get(key);
    }

    public HashMap<String, Triple<Output, Integer, Boolean>> getOutputs(){
        return this.outputs;
    }
    
    

    public void addInput(Triple<FuzzyVariable, Integer, Boolean> input) {
		this.inputs.put(input.getFirst().getName(),input);
	}

	public void addOutput(Triple<Output, Integer, Boolean> output) {
		this.outputs.put(output.getFirst().getOutputName(),output);
	}

	public Rule() {
		inputs = new HashMap<String, Triple<FuzzyVariable, Integer, Boolean>>();
		outputs = new HashMap<String, Triple<Output, Integer, Boolean>>();
		connectionMethod = ConnectionMethod.OR;
	}

    @Override
    public String getJson() {
        StringBuilder stringBuilder = new StringBuilder("{");

        stringBuilder.append("\"Connection Method\" : \"").append(connectionMethod).append("\",");
        stringBuilder.append("\"Inputs\" : [");
        if (inputs.keySet().size() > 0){
            ArrayList<String> keys = new ArrayList<String>(inputs.keySet());
            for (int i=0;i<keys.size()-1;i++){
                stringBuilder.append("{\"Name\":\"").append(keys.get(i)).append("\",");
                stringBuilder.append("\"FuzzyVariable\":").append(inputs.get(keys.get(i)).getFirst().getJson());
                stringBuilder.append(",");
                stringBuilder.append("\"Integer\":\"").append(inputs.get(keys.get(i)).getSecond()).append("\",");
                stringBuilder.append("\"Boolean\":\"").append(inputs.get(keys.get(i)).getThird()).append("\"},");
            }
                stringBuilder.append("{\"Name\":\"").append(keys.get(keys.size()-1)).append("\",");
                stringBuilder.append("\"FuzzyVariable\":").append(inputs.get(keys.get(keys.size()-1)).getFirst().getJson());
                stringBuilder.append(",");
                stringBuilder.append("\"Integer\":\"").append(inputs.get(keys.get(keys.size()-1)).getSecond()).append("\",");
                stringBuilder.append("\"Boolean\":\"").append(inputs.get(keys.get(keys.size()-1)).getThird()).append("\"}");
        }
        stringBuilder.append("],\"Outputs\" : [");
        if (outputs.keySet().size() > 0){
            ArrayList<String> keys = new ArrayList<String>(outputs.keySet());
            for (int i=0;i<keys.size()-1;i++){
                stringBuilder.append("{\"Name\":\"").append(keys.get(i)).append("\",");
                stringBuilder.append("\"Output\":").append(outputs.get(keys.get(i)).getFirst().getJson());
                stringBuilder.append(",");
                stringBuilder.append("\"Integer\":\"").append(outputs.get(keys.get(i)).getSecond()).append("\",");
                stringBuilder.append("\"Boolean\":\"").append(outputs.get(keys.get(i)).getThird()).append("\"},");
            }
                stringBuilder.append("{\"Name\":\"").append(keys.get(keys.size()-1)).append("\",");
                stringBuilder.append("\"Output\":").append(outputs.get(keys.get(keys.size()-1)).getFirst().getJson());
                stringBuilder.append(",");
                stringBuilder.append("\"Integer\":\"").append(outputs.get(keys.get(keys.size()-1)).getSecond()).append("\",");
                stringBuilder.append("\"Boolean\":\"").append(outputs.get(keys.get(keys.size()-1)).getThird()).append("\"}");
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

	private HashMap<String, Triple<FuzzyVariable, Integer, Boolean>> inputs; //Int -> mfNumber
    private HashMap<String, Triple<Output, Integer, Boolean>> outputs;
    private ConnectionMethod connectionMethod;
    // applyImplication
}
