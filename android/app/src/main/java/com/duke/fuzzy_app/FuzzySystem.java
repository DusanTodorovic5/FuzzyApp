package com.duke.fuzzy_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import com.duke.fuzzy_app.methods.*;
import com.duke.fuzzy_app.exceptions.*;
import com.duke.fuzzy_app.outputs.Mamdani;
import com.duke.fuzzy_app.outputs.Output;
import com.duke.fuzzy_app.outputs.Sugeno;

public class FuzzySystem implements JsonSerializable {

	public FuzzySystem() {
		this("Enter system name");
	}

	public FuzzySystem(String name) {
		this.name = name;
		inputs = new ArrayList<>();
		outputs = new ArrayList<Output>();
		rules = new ArrayList<>();
		andMethod = AndMethod.MIN;
		orMethod = OrMethod.MAX;
		implicationMethod = ImplicationMethod.CLIP;
		aggregationMethod = AggregationMethod.MAX;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<FuzzyVariable> getInputs() {
		return this.inputs;
	}

	public void addInput(FuzzyVariable fuzzyVariable) {
		this.inputs.add(fuzzyVariable);
	}

	public void removeInput(int index) throws NonExistingInputException {
		try {
			this.inputs.remove(index);
		} catch (Exception e) {
			throw new NonExistingInputException();
		}
	}

	public FuzzyVariable getInput(int index) throws NonExistingInputException {
		FuzzyVariable returnVariable = null;
		try {
			returnVariable = this.inputs.get(index);
		} catch (Exception e) {
			throw new NonExistingInputException();
		}
		return returnVariable;
	}

	// TODO Changed method
	public ArrayList<Output> getOutputs() {
		return this.outputs;
	}

	// TODO New method
	public Output getOutput(int index) throws NonExistingOutputException {

		Output returnVariable = null;
		try {
			returnVariable = this.outputs.get(index);
		} catch (Exception e) {
			throw new NonExistingOutputException();
		}
		return returnVariable;
	}

	// TODO Changed method
	public void setOutputType(Output output) {
		this.outputs.set(0, output);
	}

	public void addOutput(Output output) { // CHANGE INPUT PARAMETER
		this.outputs.add(output);
	}

	// TODO Changed method
	public void removeOutput(int index) throws NonExistingOutputException {
		try {
			this.outputs.remove(index);
		} catch (Exception e) {
			throw new NonExistingOutputException();
		}
	}

	// TODO Changed method
	public String getOutputType() {
		try {
			return this.outputs.get(0).getClass().getName();
		} catch (Exception e) {

		}
		return "lib.outputs.Mamdani";
	}

	public Double applyAndConnectionMethod(ArrayList<Double> values) {
		Double product = 1.0;
		if (this.andMethod.equals(AndMethod.MIN)) {
			product = Collections.min(values);
		} else {
			for (Double value : values) {
				product *= value;
			}
		}
		return product;
	}

	public Double applyOrConnectionMethod(ArrayList<Double> values) {
		Double product = 0.0;
		if (this.orMethod.equals(OrMethod.MAX)) {
			product = Collections.max(values);
		} else {
			for (Double value : values) {
				product += value;
			}
			Double tmp = 1.0;
			for (Double value : values) {
				tmp *= value;
			}
			product /= tmp;
		}
		return product;
	}

	// Apply implication for one data point of membership function
	public Double applyImplicationMethod(Double dataPoint, Double implicationValue) {
		switch (getImplicationMethod()) {
		case CLIP:
			return dataPoint < implicationValue ? dataPoint : implicationValue;
		case SCALE:
			return dataPoint * implicationValue;
		}
		return 0.0;
	}

	// TODO Change place
	public static double[] linspace(double min, double max, int points) {
		double[] d = new double[points];
		for (int i = 0; i < points; i++) {
			d[i] = min + i * (max - min) / (points - 1);
		}
		return d;
	}
	private HashMap<String, Double> evaluateMamdani(HashMap<String, Double> values) {
		ArrayList<HashMap<String, double[]>> hashMapList = new ArrayList<HashMap<String, double[]>>(); // hash map list of all rules
		HashMap<String, double[]> outputXaxis = new HashMap<String, double[]>();
		HashMap<String, double[]> outputYaxis = new HashMap<String, double[]>(); // final output points
		ArrayList<Double> ruleInput = new ArrayList<Double>(); // Final input values of all rules after connection
		for (Output out : outputs) {
			Mamdani mam = (Mamdani) out;
			// Put x axis of every output in this map
			double[] d = mam.linspace(POINTS_N); // hardcoded
			outputXaxis.put(mam.getOutputName(), d);
			double[] y = new double[POINTS_N];
			Arrays.fill(y, -Double.MAX_VALUE); // fill y with smallest values
			outputYaxis.put(mam.getOutputName(), y); // fill y with smallest values
		}
		for (Rule rule : rules) {
			ArrayList<String> inputKeys = new ArrayList<String>(values.keySet()); // Input keys of one rule
			ArrayList<String> outputKeys = new ArrayList<String>(rule.getOutputs().keySet()); // output keys of one rule
			ArrayList<Double> valuesOfRule = new ArrayList<Double>(); // Fuzzified input values of one rule
			// Fuzzification
			for (String key : inputKeys) {
				Triple<FuzzyVariable, Integer, Boolean> triple = rule.getInput(key);
				if (triple != null) {
					valuesOfRule
							.add(triple.getThird() ? 1 - triple.getFirst().fuzzify(values.get(key), triple.getSecond())
									: triple.getFirst().fuzzify(values.get(key), triple.getSecond()));
				}
			}
			// Connection

			switch (rule.getConnectionMethod()) {
				case AND:
					ruleInput.add(applyAndConnectionMethod(valuesOfRule));
					break;
				case OR:
					ruleInput.add(applyOrConnectionMethod(valuesOfRule));
					break;
			}

			// Implication

			HashMap<String, double[]> outputMap = new HashMap<String, double[]>(); // y axis after implication
			// Generated x axis in outputXaxis
			for (String outKey : outputKeys) {
				double[] y = new double[POINTS_N]; // hardcoded
				for (int i = 0; i < POINTS_N; i++) {
					Triple<Output, Integer, Boolean> triple = rule.getOutput(outKey);
					y[i] = triple.getThird()
							? 1 - triple.getFirst().fuzzify(outputXaxis.get(outKey)[i], triple.getSecond())
							: triple.getFirst().fuzzify(outputXaxis.get(outKey)[i], triple.getSecond());
					switch (implicationMethod) {
						case CLIP: //max
							y[i] = ruleInput.get(ruleInput.size() - 1)> y[i]?y[i]:ruleInput.get(ruleInput.size() - 1);
							break;
							case SCALE: // prod
								y[i] = ruleInput.get(ruleInput.size() - 1) * y[i];
								break;
					}
				}
				outputMap.put(outKey, y);
			}
			hashMapList.add(outputMap);
		}
		// Aggregation for Mamdani
		for (Output output : outputs) { // for each output
			for (HashMap<String, double[]> map : hashMapList) { // for each map in list
				double[] d = map.get(output.getOutputName()); // returns y axis of output in the list
				if (d != null) { // if exists
					for (int i = 0; i < POINTS_N; i++) {
						outputYaxis.get(output.getOutputName())[i] = Math.max(d[i], outputYaxis.get(output.getOutputName())[i]); // update Y values of each output
					}
				}
			}
		}
		// Defuzzification for Mamdani
		HashMap<String, Double> defuzzifiedValues = new HashMap<String, Double>();
		for (Output out : outputs) {
			double res = 0.0;
			double arrSum = 0.0;
			for (int i = 0; i < POINTS_N; i++) {
				res += outputYaxis.get(out.getOutputName())[i] * outputXaxis.get(out.getOutputName())[i];
				arrSum += outputYaxis.get(out.getOutputName())[i];
			}
			res /= arrSum;
			defuzzifiedValues.put(out.getOutputName(), res);
		}
		return defuzzifiedValues;}


	private HashMap<String, Double> evaluateSugeno(HashMap<String, Double> values) {
		HashMap<String, HashMap<Integer, Double>> resultMap = new HashMap<String, HashMap<Integer, Double>>();
		ArrayList<Double> ruleInput = new ArrayList<Double>(); // Final input values of all rules after connection
		for(Rule rule: rules){
			ArrayList<String> inputKeys = new ArrayList<String>(values.keySet()); // Input keys of one rule
			ArrayList<String> outputKeys = new ArrayList<String>(rule.getOutputs().keySet()); // output keys of one rule
			ArrayList<Double> valuesOfRule = new ArrayList<Double>(); // Fuzzified input values of one rule
			// Fuzzification
			for (String key : inputKeys) {
				Triple<FuzzyVariable, Integer, Boolean> triple = rule.getInput(key);
				if (triple != null) {
					valuesOfRule
							.add(triple.getThird() ? 1 - triple.getFirst().fuzzify(values.get(key), triple.getSecond())
									: triple.getFirst().fuzzify(values.get(key), triple.getSecond()));
				}
			}
			// Connection

			switch (rule.getConnectionMethod()) {
				case AND : {ruleInput.add(applyAndConnectionMethod(valuesOfRule)); break;}
				case OR : {ruleInput.add(applyOrConnectionMethod(valuesOfRule)); break; }
			}
			for (String outKey : outputKeys) {
				Triple<Output, Integer, Boolean> triple = rule.getOutput(outKey);
				HashMap<Integer, Double> outMap = resultMap.get(outKey);
				if(outMap==null){
					HashMap<Integer, Double> newMap = new HashMap<Integer, Double>();
					resultMap.put(outKey, newMap);
					outMap = resultMap.get(outKey);
				}
					double finalVal = triple.getThird()?1-ruleInput.get(ruleInput.size()-1):ruleInput.get(ruleInput.size()-1);
					Double curVal = outMap.get(triple.getSecond());
					if(curVal==null){
						outMap.put(triple.getSecond(), finalVal);
					}else{
						outMap.put(triple.getSecond(), curVal+finalVal);
					}

			}
		}
		// Defuzzification
		HashMap<String, Double> defuzzifiedValues = new HashMap<String, Double>();
		for(Output output: outputs){
			double num=0.0;
			double den=0.0;
			for(Integer mfIndex: resultMap.get(output.getOutputName()).keySet()){
				try {
					num += resultMap.get(output.getOutputName()).get(mfIndex)*output.getOutput(mfIndex);
					den += resultMap.get(output.getOutputName()).get(mfIndex);
				} catch (NonExistingOutputException e) {
					e.printStackTrace();
				}
			}
			double ans = num/den;
			defuzzifiedValues.put(output.getOutputName(), ans);
		}
		return defuzzifiedValues;
	}

	// Map of input values -> map of output values
	public HashMap<String, Double> evaluate(HashMap<String, Double> values) { // input->value => output->value
		if (getOutputType()=="lib.outputs.Mamdani") return evaluateMamdani(values);
		else return evaluateSugeno(values);
	}

	public ArrayList<Rule> getRules() {
		return this.rules;
	}

	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	public void removeRule(int index) throws NonExistingRuleException {
		try {
			this.rules.remove(index);
		} catch (Exception e) {
			throw new NonExistingRuleException();
		}
	}

	public Rule getRule(int index) throws NonExistingRuleException {
		Rule returnRule = null;
		try {
			returnRule = this.rules.get(index);
		} catch (Exception e) {
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
		StringBuilder stringBuilder = new StringBuilder("{");
		// I do not save POINTS_N here, different value will increase or decrease accuracy but will not change functionality
		stringBuilder.append("\"Name\" : \"").append(this.name).append("\",");
		stringBuilder.append("\"And Method\" : \"").append(this.andMethod).append("\",");
		stringBuilder.append("\"Or Method\" : \"").append(this.orMethod).append("\",");
		stringBuilder.append("\"Implication Method\" : \"").append(this.aggregationMethod).append("\",");
		stringBuilder.append("\"Input\" : [");
		//Not using iterator here, it's simple array and I need to know the last element, faster and easier to just find last i
		if (this.inputs.size()>0){
			for (int i=0;i<this.inputs.size()-1;i++){
				stringBuilder.append(this.inputs.get(i).getJson()).append(",");
			}
			//adding last input from array, will not cause exception as input.size() must be greater than 0
			stringBuilder.append(this.inputs.get(this.inputs.size() - 1).getJson());
		}
		stringBuilder.append("],");	

		stringBuilder.append("\"Output\" : [");
		if (this.outputs.size()>0){
			for (int i=0;i<this.outputs.size()-1;i++){
				stringBuilder.append(this.outputs.get(i).getJson()).append(",");
			}
			stringBuilder.append(this.outputs.get(this.outputs.size() - 1).getJson());
		}
		stringBuilder.append("],");

		stringBuilder.append("\"Rule\" : [");
		if (this.rules.size()>0){
			for (int i=0;i<this.rules.size()-1;i++){
				stringBuilder.append(this.rules.get(i).getJson()).append(",");
			}
			stringBuilder.append(this.rules.get(this.rules.size() - 1).getJson());
		}
		stringBuilder.append("]");
		
        stringBuilder.append("}");
        return stringBuilder.toString();
	}

	private String name;

	private ArrayList<FuzzyVariable> inputs;

	private ArrayList<Output> outputs;

	private ArrayList<Rule> rules;

	private AndMethod andMethod;

	private OrMethod orMethod;

	private ImplicationMethod implicationMethod;

	private AggregationMethod aggregationMethod;

	private int POINTS_N = 5000;

}
