package com.duke.fuzzyapp.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import com.duke.fuzzyapp.lib.exceptions.NonExistingFuzzySystemException;
import com.duke.fuzzyapp.lib.membershipFunctions.MembershipFunction;
import com.duke.fuzzyapp.lib.outputs.Output;

import org.reflections.*;

public class FuzzyLibraryController {

    public void load(){

    }

    public String getJson(){
        return "";
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
        Reflections reflections = new Reflections("com.mycompany");    
        Set<Class<? extends Output>> classes = reflections.getSubTypesOf(Output.class);


        return Arrays.copyOf(classes.toArray(), classes.toArray().length, String[].class);
    }
    //SREDI LEPO
    public static String[] getMembershipFunctions(){
        Reflections reflections = new Reflections("com.mycompany");
        Set<Class<? extends MembershipFunction>> classes = reflections.getSubTypesOf(MembershipFunction.class);


        return Arrays.copyOf(classes.toArray(), classes.toArray().length, String[].class);
    }

    public static FuzzyLibraryController getInstance(){
        if (instance == null){
            instance = new FuzzyLibraryController();
        }
        return instance;
    }

    private FuzzyLibraryController(){

    }

    private ArrayList<FuzzySystem> fuzzySystems;
    private static FuzzyLibraryController instance = null;
}
