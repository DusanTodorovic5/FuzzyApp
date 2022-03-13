package lib.membershipFunctions;

import java.util.ArrayList;

public class TrapezoidMembershipFunction extends MembershipFunction {

    public TrapezoidMembershipFunction(){
        super(4);
    }

    @Override
    public String getJson() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double fuzzify(double number) {
        if (number < parameters[0]) {
            return 0;
        }
        if (number < parameters[1]){
            return (number - parameters[0])/(parameters[1] - parameters[0]);
        }
        if (number < parameters[2]){
            return 1;
        }
        if (number < parameters[3]){
            return (parameters[3] - number)/(parameters[3] - parameters[2]);
        }
        return 0;
    }

    @Override
    public double deFuzzify(double number) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double[] applyImplication() {
        // TODO Auto-generated method stub
        return null;
    }
}