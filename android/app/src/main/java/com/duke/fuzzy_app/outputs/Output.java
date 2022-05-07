package com.duke.fuzzy_app.outputs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.duke.fuzzy_app.JsonSerializable;
import com.duke.fuzzy_app.exceptions.NonExistingOutputException;

public interface Output extends JsonSerializable {
    public void addOutput();
    public void removeOutput(int index) throws NonExistingOutputException;
    public double getOutput(int index) throws NonExistingOutputException;
    public void evaluate();
    public void calculate();
    public String getOutputName();
    public double fuzzify(double number, int mfNumber);
    public static Output parseJSON(String json) throws JSONException {
        JSONObject output = new JSONObject(json);
        Output op;
        if (output.getString("Type").equals("S")) {
            op = new Sugeno(output.getString("Name"));
            JSONArray functions = output.getJSONArray("Functions");
            for (int k = 0; k < functions.length(); k++) {
                Function f = new Function(
                    functions.getJSONObject(k).getString("Name"),
                    Double.parseDouble(functions.getJSONObject(k).getString("Value")));
                ((Sugeno)op).addFunction(f);
            }
        } 
        else {
            op = new Mamdani(output.getString("Name"));

            // TODO CREATE MAMDANI PARSE
        }

        return op;
    }
}
