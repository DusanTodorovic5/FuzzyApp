package lib;

import java.util.HashMap;

import lib.methods.ConnectionMethod;
import lib.outputs.Output;

public class Rule implements JsonSerializable{
    @Override
    public String getJson() {
        // TODO Auto-generated method stub
        return null;
    }

    public ConnectionMethod getConnectionMethod() {
        return this.connectionMethod;
    }

    public void setConnectionMethod(ConnectionMethod connectionMethod) {
        this.connectionMethod = connectionMethod;
    }

    

    public Triple<FuzzyVariable,Integer,Boolean> getInput(String key){
        return inputs.get(key);
    }
    
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

	private HashMap<String, Triple<FuzzyVariable, Integer, Boolean>> inputs; //Int -> mfNumber
    private HashMap<String, Triple<Output, Integer, Boolean>> outputs;
    private ConnectionMethod connectionMethod;
    // applyImplication
}
