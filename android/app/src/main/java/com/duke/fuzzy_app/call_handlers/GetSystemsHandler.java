package com.duke.fuzzy_app.call_handlers;

import androidx.annotation.NonNull;

import com.duke.fuzzy_app.FuzzyLibraryController;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class GetSystemsHandler extends SpecialMethodCallHandler {
    public GetSystemsHandler(String name) {
        super(name);
    }

    @Override
    public Object methodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        return FuzzyLibraryController.getInstance().getFuzzySystems();
    }
}


/*
import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class AddSystemHandler implements MethodChannel.MethodCallHandler {
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {

    }
}
 */