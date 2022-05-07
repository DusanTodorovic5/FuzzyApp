package com.duke.fuzzy_app;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.duke.fuzzy_app.exceptions.NonExistingMembershipFunctionException;
import com.duke.fuzzy_app.membershipFunctions.MembershipFunction;
import com.duke.fuzzy_app.membershipFunctions.TrapezoidMembershipFunction;
import com.duke.fuzzy_app.membershipFunctions.TriangleMembershipFunction;

public class FuzzyVariable implements JsonSerializable {

  public FuzzyVariable() { this("Enter name"); }

  public FuzzyVariable(String name) {
    this.name = name;
    this.start = 0;
    this.end = 0;
    this.membershipFunctions = new ArrayList<MembershipFunction>();
  }

  public double fuzzify(double number, int mfNumber) {
    return membershipFunctions.get(mfNumber).fuzzify(number);
  }

  public double deFuzzify(double number, double mfNumber) { return number; }

  public void addMembershipFunction(MembershipFunction membershipFunction) {
    this.membershipFunctions.add(membershipFunction);
  }

  public void removeMembershipFunction(int index)
      throws NonExistingMembershipFunctionException {
    try {
      this.membershipFunctions.remove(index);
    } catch (Exception e) {
      throw new NonExistingMembershipFunctionException();
    }
  }

  public MembershipFunction getMembershipFunction(int index)
      throws NonExistingMembershipFunctionException {
    MembershipFunction membershipFunction = null;
    try {
      membershipFunction = this.membershipFunctions.get(index);
    } catch (Exception e) {
      throw new NonExistingMembershipFunctionException();
    }
    return membershipFunction;
  }

  @Override
  public String getJson() {
    StringBuilder stringBuilder = new StringBuilder("{");
    stringBuilder.append("\"Name\":\"").append(this.name).append("\",");
    stringBuilder.append("\"Start\":\"").append(this.start).append("\",");
    stringBuilder.append("\"End\":\"").append(this.end).append("\",");
    stringBuilder.append("\"Membership Functions\":[");
    if (membershipFunctions.size() > 0) {
      for (int i = 0; i < membershipFunctions.size() - 1; i++) {
        stringBuilder.append(membershipFunctions.get(i).getJson()).append(",");
      }
      stringBuilder.append(
          membershipFunctions.get(membershipFunctions.size() - 1).getJson());
    }
    stringBuilder.append("]");
    stringBuilder.append("}");
    return stringBuilder.toString();
  }

  public static FuzzyVariable parseJson(String json) {
    try{
      JSONObject input = new JSONObject(json);
      FuzzyVariable fv = new FuzzyVariable(input.getString("Name"));

      fv.setEnd(Double.parseDouble(input.getString("End")));
      fv.setStart(Double.parseDouble(input.getString("Start")));

      JSONArray mfs = input.getJSONArray("Membership Functions");
      for (int k = 0; k < mfs.length(); k++) {
        JSONObject mfjo = mfs.getJSONObject(k);
        MembershipFunction mf = null;
        if (mfjo.getString("Type").equals("Triangle")) {
          mf = new TriangleMembershipFunction();
        } else {
          mf = new TrapezoidMembershipFunction();
        }

        mf.setName(mfjo.getString("Name"));

        JSONArray parametersJson = mfjo.getJSONArray("Parameters");
        for (int t = 0; t < parametersJson.length(); t++) {
          mf.addParameters(Double.parseDouble(parametersJson.getString(t)), t);
        }

        fv.addMembershipFunction(mf);
      }

      return fv;
    } catch (Exception e){

    }
    return null;

  }

  public double getStart() { return this.start; }

  public void setStart(double start) { this.start = start; }

  public double getEnd() { return this.end; }

  public void setEnd(double end) { this.end = end; }

  public String getName() { return this.name; }

  public void setName(String name) { this.name = name; }

  private double start;
  private double end;
  private String name;
  private ArrayList<MembershipFunction> membershipFunctions;
}
