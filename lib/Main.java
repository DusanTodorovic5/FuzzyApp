package lib;

import lib.exceptions.NonExistingFuzzySystemException;
import lib.membershipFunctions.*;

import java.util.ArrayList;
import java.util.HashMap;

import lib.exceptions.*;
import lib.methods.*;
import lib.outputs.*;
public class Main {
	public static void main(String args[]) {
		try {
			FuzzyLibraryController.getInstance().addFuzzySystem();
			FuzzySystem mojSistem = FuzzyLibraryController.getInstance().editFuzzySystem(0);
			
			mojSistem.setName("FS Test");
			
			FuzzyVariable fv = new FuzzyVariable("FV Test");
			FuzzyVariable fv2 = new FuzzyVariable("FV2 Test");
			
			Mamdani out = new Mamdani("OUT TEST");
			out.getVariable().setEnd(100);
			out.getVariable().setStart(20);
			MembershipFunction of11 = new TrapezoidMembershipFunction();
			of11.addParameters(20, 0);
			of11.addParameters(40, 1);
			of11.addParameters(60, 2);
			of11.addParameters(80, 3);
			out.addMembershipFunction(of11);
			
			mojSistem.addInput(fv);
			mojSistem.addInput(fv2);
			mojSistem.addOutput(out);
			
			System.out.println("INPUTS");
			ArrayList<FuzzyVariable> fvs = mojSistem.getInputs();
			
			
			
			for (FuzzyVariable f : fvs) {
				System.out.println(f.getName());
			}
			fvs.get(0).setStart(0);
			fvs.get(0).setEnd(10);
			
			MembershipFunction mf11 = new TrapezoidMembershipFunction();
			mf11.addParameters(0, 0);
			mf11.addParameters(2, 1);
			mf11.addParameters(4, 2);
			mf11.addParameters(8, 3);
			MembershipFunction mf12 = new TriangleMembershipFunction();
			mf12.addParameters(3, 0);
			mf12.addParameters(5, 1);
			mf12.addParameters(9, 2);
			fvs.get(0).addMembershipFunction(mf11);
			fvs.get(0).addMembershipFunction(mf12);
			
			
			fvs.get(1).setStart(0);
			fvs.get(1).setEnd(20);
			
			MembershipFunction mf21 = new TrapezoidMembershipFunction();
			mf21.addParameters(0, 0);
			mf21.addParameters(3, 1);
			mf21.addParameters(4, 2);
			mf21.addParameters(9, 3);
			fvs.get(1).addMembershipFunction(mf21);
			
			System.out.println("OUTPUTS");
			ArrayList<Output> outs = mojSistem.getOutputs();
			
			for (Output o : outs) {
				System.out.println(o.getOutputName());
			}
			
			Rule rule = new Rule();
			
			rule.addInput(new Triple<FuzzyVariable,Integer,Boolean>(fv,1,false));
			rule.addInput(new Triple<FuzzyVariable,Integer,Boolean>(fv2,0,true));
			rule.addOutput(new Triple<Output,Integer,Boolean>(out,0,false));
			mojSistem.addRule(rule);
			
			HashMap<String, Double> mapa = new HashMap<String,Double>();
			mapa.put("FV Test",4.0);
			mapa.put("FV2 Test",5.0);
			
			System.out.println("###################");
			HashMap<String,Double> mapica = mojSistem.evaluate(mapa);
			for (String key : mapica.keySet()) {
				System.out.println(key + "  " + mapica.get(key));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
