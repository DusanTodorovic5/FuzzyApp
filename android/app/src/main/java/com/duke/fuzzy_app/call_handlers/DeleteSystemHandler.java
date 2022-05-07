package com.duke.fuzzy_app.call_handlers;
import androidx.annotation.NonNull;

import com.duke.fuzzy_app.FuzzyLibraryController;
import com.duke.fuzzy_app.exceptions.NonExistingFuzzySystemException;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class DeleteSystemHandler extends SpecialMethodCallHandler {

    public DeleteSystemHandler(String name) {
        super(name);
    }

    @Override
    public Object methodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) throws NonExistingFuzzySystemException {
        int index = call.argument("index");
        FuzzyLibraryController.getInstance().removeFuzzySystem(index);
        return 1;
    }
}