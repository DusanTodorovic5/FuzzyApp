package lib;
import java.util.ArrayList;

import lib.exceptions.NonExistingMembershipFunctionException;
import lib.membershipFunctions.MembershipFunction;

public class FuzzyVariable implements JsonSerializable{
    
    public FuzzyVariable(){
        this("Enter name");
    }

    public FuzzyVariable(String name){
        this.name = name;
        this.start = 0;
        this.end = 0;
        this.membershipFunctions = new ArrayList();
    }

    public double fuzzify(double number, int mfNumber){
        return membershipFunctions.get(mfNumber).fuzzify(number);
    }

    public double deFuzzify(double number, double mfNumber){
        return number;
    }

    public void addMembershipFunction(MembershipFunction membershipFunction){
        this.membershipFunctions.add(membershipFunction);
    }

    public void removeMembershipFunction(int index) throws NonExistingMembershipFunctionException{
        try{
            this.membershipFunctions.remove(index);
        }
        catch(Exception e){
            throw new NonExistingMembershipFunctionException();
        }
    }

    public MembershipFunction getMembershipFunction(int index) throws NonExistingMembershipFunctionException{
        MembershipFunction membershipFunction = null;
        try{
            membershipFunction = this.membershipFunctions.get(index);
        }
        catch(Exception e){
            throw new NonExistingMembershipFunctionException();
        }
        return membershipFunction;
    }

    @Override
    public String getJson() {
        // TODO Auto-generated method stub
        return null;
    }

    public double getStart() {
        return this.start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return this.end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double start;
    private double end;
    private String name;
    private ArrayList<MembershipFunction> membershipFunctions;
}
