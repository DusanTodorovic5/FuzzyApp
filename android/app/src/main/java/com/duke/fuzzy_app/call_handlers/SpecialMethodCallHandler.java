package com.duke.fuzzy_app.call_handlers;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public abstract class SpecialMethodCallHandler implements MethodChannel.MethodCallHandler{

    public SpecialMethodCallHandler(String name){
        this.name = name;
    }
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        if (call.method.equals(this.name)) {
            try{
                result.success(methodCall(call, result));
            }
            catch (Exception e){
                result.error("UNAVAILABLE", e.toString(), null);
            }

        } else {
            result.notImplemented();
        }
    }

    public abstract Object methodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) throws Exception;

    private String name;
}
