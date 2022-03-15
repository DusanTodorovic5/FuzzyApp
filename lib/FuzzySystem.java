package lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import lib.methods.*;
import lib.exceptions.*;
import lib.outputs.Mamdani;
import lib.outputs.Output;

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

	// TODO
	// Map of input values -> map of output values
	public HashMap<String, Double> evaluate(HashMap<String, Double> values) { // input->value => output->value
		ArrayList<HashMap<String, double[]>> hashMapList = new ArrayList<HashMap<String, double[]>>(); // hash map list of all rules
		HashMap<String, double[]> outputXaxis = new HashMap<String, double[]>();
		HashMap<String, double[]> outputYaxis = new HashMap<String, double[]>(); // final output points
		if (getOutputType() == "lib.outputs.Mamdani") {
			for (Output out : outputs) {
				Mamdani mam = (Mamdani) out;
				// Put x axis of every output in this map
				double[] d = mam.linspace(POINTS_N); // hardcoded
				outputXaxis.put(mam.getOutputName(), d);
				double[] y = new double[POINTS_N];
				Arrays.fill(y, -Double.MAX_VALUE); // fill y with smallest values
				outputYaxis.put(mam.getOutputName(), y); // fill y with smallest values
			}
		} else if (getOutputType() == "lib.outputs.Sugeno") { // final values are held in outputYaxis
			for (Output out : outputs) {
				// double[] d = {out.getOutput(out)};
			}
		}
		ArrayList<Double> ruleInput = new ArrayList<Double>(); // Final input values of all rules after connection
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

			// TODO Changed to switch
			// if (rule.getConnectionMethod().equals(ConnectionMethod.AND)){
			// ruleInput.add(applyAndConnectionMethod(valuesOfRule));
			// }
			// else{
			// ruleInput.add(applyOrConnectionMethod(valuesOfRule));
			// }
			switch (rule.getConnectionMethod()) {
			case AND:
				ruleInput.add(applyAndConnectionMethod(valuesOfRule));
				break;
			case OR:
				ruleInput.add(applyOrConnectionMethod(valuesOfRule));
				break;
			}

			// Implication

			// TODO Changed to switch: maybe unnecessary
			// if (getOutputType().equals("Mamdani")){

			// }
			// else{

			// }
			HashMap<String, double[]> outputMap = new HashMap<String, double[]>(); // y axis after implication
			switch (getOutputType()) {
			case "lib.outputs.Mamdani": // Implication, Aggregation and Defuzzification left
				// Generated x axis in outputXaxis
				for (String outKey : outputKeys) {
					double[] y = new double[POINTS_N]; // hardcoded
					for (int i = 0; i < POINTS_N; i++) {
						Triple<Output, Integer, Boolean> triple = rule.getOutput(outKey);
						y[i] = triple.getThird()
								? 1 - triple.getFirst().fuzzify(outputXaxis.get(outKey)[i], triple.getSecond())
								: triple.getFirst().fuzzify(outputXaxis.get(outKey)[i], triple.getSecond());
						switch (implicationMethod) {
						case CLIP:
							y[i] = ruleInput.get(ruleInput.size() - 1)> y[i]?y[i]:ruleInput.get(ruleInput.size() - 1);
							break;
						case SCALE:
							y[i] = ruleInput.get(ruleInput.size() - 1) * y[i];
							break;
						}

					}
					outputMap.put(outKey, y);
				}
				hashMapList.add(outputMap);

				break;
			case "lib.outputs.Sugeno": // Implication, Aggregation and Defuzzification left
				// Implication
				HashMap<String, double[]> sugenoMap = new HashMap<String, double[]>(); // map for
																						// sugeno
				try {																		// output
				for (String outKey : outputKeys) { // for each output in rule
					double[] p ={
								rule.getOutput(outKey).getFirst().getOutput(rule.getOutput(outKey).getSecond()),
								ruleInput.get(ruleInput.size()-1)
								}; // pair k-v
					sugenoMap.put(outKey, p);
				}
				}
				catch (Exception e) {
					
				}
				hashMapList.add(sugenoMap);
				break;
			}
		}
		// Aggregation for Mamdani
		switch (getOutputType()) {
		case "lib.outputs.Mamdani":
			for (Output output : outputs) { // for each output
				for (HashMap<String, double[]> map : hashMapList) { // for each map in list
					double[] d = map.get(output.getOutputName()); // returns y axis of output in the list
					if (d != null) { // if exists
						for (int i = 0; i < POINTS_N; i++) {
							outputYaxis.get(output.getOutputName())[i] = d[i]>
									outputYaxis.get(output.getOutputName())[i]?d[i]:outputYaxis.get(output.getOutputName())[i]; // update Y values of each output
						}
					}
				}

			}
			// Agregation result is outputYaxis
			break;
		case "lib.outputs.Sugeno":
			for (Output output : outputs) { // for every output
				for (HashMap map : hashMapList) { // for every map in list string-> double[2]

				}
			}
			break;
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
		return defuzzifiedValues;

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
		// TODO Auto-generated method stub
		return null;
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
