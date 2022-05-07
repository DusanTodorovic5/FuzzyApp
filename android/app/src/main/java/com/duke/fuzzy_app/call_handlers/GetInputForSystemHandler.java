package com.duke.fuzzy_app.call_handlers;

import androidx.annotation.NonNull;

import com.duke.fuzzy_app.FuzzyLibraryController;
import com.duke.fuzzy_app.FuzzySystem;
import com.duke.fuzzy_app.FuzzyVariable;
import com.duke.fuzzy_app.exceptions.NonExistingFuzzySystemException;

import java.util.ArrayList;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class GetInputForSystemHandler extends SpecialMethodCallHandler {

    public GetInputForSystemHandler(String name) {
        super(name);
    }

    @Override
    public Object methodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) throws NonExistingFuzzySystemException {
        ArrayList<String> inputs = new ArrayList<>();
        int index = call.argument("index");

        FuzzySystem fs = FuzzyLibraryController.getInstance().editFuzzySystem(index);
        ArrayList<FuzzyVariable> list = fs.getInputs();
        for (FuzzyVariable fv : list){
            inputs.add(fv.getName());
        }

        return inputs;
    }
}
