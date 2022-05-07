package com.duke.fuzzy_app.call_handlers;

import androidx.annotation.NonNull;

import com.duke.fuzzy_app.FuzzyLibraryController;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class LoadHandler extends SpecialMethodCallHandler {
    public LoadHandler(String name) {
        super(name);
    }

    @Override
    public Object methodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        String json = call.argument("json");
        FuzzyLibraryController.getInstance().load(json);
        return 1;
    }
}
