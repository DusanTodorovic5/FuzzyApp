package com.duke.fuzzy_app;

import androidx.annotation.NonNull;

import com.duke.fuzzy_app.call_handlers.*;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL_LOAD = "fuzzy_plugin/load";
    private static final String CHANNEL_GET_SYSTEMS = "fuzzy_plugin/get_systems";
    private static final String CHANNEL_ADD_SYSTEMS = "fuzzy_plugin/add_systems";
    private static final String CHANNEL_DELETE_SYSTEM = "fuzzy_plugin/delete_system";
    private static final String CHANNEL_GET_SYSTEM_INPUTS = "fuzzy_plugin/get_system_inputs";
    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_LOAD).setMethodCallHandler(new LoadHandler("load"));
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_GET_SYSTEMS).setMethodCallHandler(new GetSystemsHandler("get_systems"));
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_ADD_SYSTEMS).setMethodCallHandler(new AddSystemHandler("add_systems"));
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_DELETE_SYSTEM).setMethodCallHandler(new DeleteSystemHandler("delete_system"));
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_GET_SYSTEM_INPUTS).setMethodCallHandler(new GetInputForSystemHandler("get_system_inputs"));
    }


}
