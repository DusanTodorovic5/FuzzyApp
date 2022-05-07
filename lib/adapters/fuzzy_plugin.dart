import 'package:flutter/services.dart';
import 'package:fuzzy_app/classes/fuzzy_system.dart';

import '../classes/fuzzy_input.dart';

class FuzzyPlugin {
  static const platformLoad = MethodChannel('fuzzy_plugin/load');
  static const platformGetSystems = MethodChannel('fuzzy_plugin/get_systems');
  static const platformAddSystems = MethodChannel('fuzzy_plugin/add_systems');
  static const platformDeleteSystem =
      MethodChannel('fuzzy_plugin/delete_system');
  static const platformGetSystemInputs =
      MethodChannel('fuzzy_plugin/get_system_inputs');

  static Future<void> load() async {
    try {
      String json =
          "[{\"Name\" : \"Sugeno test system\",\"And Method\" : \"MIN\",\"Or Method\" : \"MAX\",\"Implication Method\" : \"MAX\",\"Input\" : [{\"Name\":\"A\",\"Start\":\"10.0\",\"End\":\"100.0\",\"Membership Functions\":[{\"Type\":\"Triangle\",\"Name\":\"null\",\"Parameters\":[\"10.0\",\"30.0\",\"40.0\"]},{\"Type\":\"Trapezoid\",\"Name\":\"null\",\"Parameters\":[\"30.0\",\"60.0\",\"80.0\",\"100.0\"]}]},{\"Name\":\"B\",\"Start\":\"30.0\",\"End\":\"50.0\",\"Membership Functions\":[{\"Type\":\"Triangle\",\"Name\":\"null\",\"Parameters\":[\"30.0\",\"35.0\",\"40.0\"]},{\"Type\":\"Trapezoid\",\"Name\":\"null\",\"Parameters\":[\"35.0\",\"40.0\",\"45.0\",\"50.0\"]}]}],\"Output\" : [{\"Type\":\"S\",\"Name\":\"Sugeno out1\",\"Functions\":[{\"Name\" : \"Function 1 Name\",\"Value\" : \"4.0\"},{\"Name\" : \"Function 1 Name\",\"Value\" : \"8.0\"},{\"Name\" : \"Function 2 Name\",\"Value\" : \"10.0\"}]},{\"Type\":\"S\",\"Name\":\"Sugeno out2\",\"Functions\":[{\"Name\" : \"Function 1 Name out2\",\"Value\" : \"10.0\"},{\"Name\" : \"Function 2 Name out2\",\"Value\" : \"20.0\"}]}],\"Rule\" : [{\"Connection Method\" : \"AND\",\"Inputs\" : [{\"Name\":\"A\",\"FuzzyVariable\":{\"Name\":\"A\",\"Start\":\"10.0\",\"End\":\"100.0\",\"Membership Functions\":[{\"Type\":\"Triangle\",\"Name\":\"null\",\"Parameters\":[\"10.0\",\"30.0\",\"40.0\"]},{\"Type\":\"Trapezoid\",\"Name\":\"null\",\"Parameters\":[\"30.0\",\"60.0\",\"80.0\",\"100.0\"]}]},\"Integer\":\"0\",\"Boolean\":\"false\"},{\"Name\":\"B\",\"FuzzyVariable\":{\"Name\":\"B\",\"Start\":\"30.0\",\"End\":\"50.0\",\"Membership Functions\":[{\"Type\":\"Triangle\",\"Name\":\"null\",\"Parameters\":[\"30.0\",\"35.0\",\"40.0\"]},{\"Type\":\"Trapezoid\",\"Name\":\"null\",\"Parameters\":[\"35.0\",\"40.0\",\"45.0\",\"50.0\"]}]},\"Integer\":\"0\",\"Boolean\":\"false\"}],\"Outputs\" : [{\"Name\":\"Sugeno out1\",\"Output\":{\"Type\":\"S\",\"Name\":\"Sugeno out1\",\"Functions\":[{\"Name\" : \"Function 1 Name\",\"Value\" : \"4.0\"},{\"Name\" : \"Function 1 Name\",\"Value\" : \"8.0\"},{\"Name\" : \"Function 2 Name\",\"Value\" : \"10.0\"}]},\"Integer\":\"0\",\"Boolean\":\"false\"},{\"Name\":\"Sugeno out2\",\"Output\":{\"Type\":\"S\",\"Name\":\"Sugeno out2\",\"Functions\":[{\"Name\" : \"Function 1 Name out2\",\"Value\" : \"10.0\"},{\"Name\" : \"Function 2 Name out2\",\"Value\" : \"20.0\"}]},\"Integer\":\"0\",\"Boolean\":\"false\"}]},{\"Connection Method\" : \"AND\",\"Inputs\" : [{\"Name\":\"A\",\"FuzzyVariable\":{\"Name\":\"A\",\"Start\":\"10.0\",\"End\":\"100.0\",\"Membership Functions\":[{\"Type\":\"Triangle\",\"Name\":\"null\",\"Parameters\":[\"10.0\",\"30.0\",\"40.0\"]},{\"Type\":\"Trapezoid\",\"Name\":\"null\",\"Parameters\":[\"30.0\",\"60.0\",\"80.0\",\"100.0\"]}]},\"Integer\":\"1\",\"Boolean\":\"true\"},{\"Name\":\"B\",\"FuzzyVariable\":{\"Name\":\"B\",\"Start\":\"30.0\",\"End\":\"50.0\",\"Membership Functions\":[{\"Type\":\"Triangle\",\"Name\":\"null\",\"Parameters\":[\"30.0\",\"35.0\",\"40.0\"]},{\"Type\":\"Trapezoid\",\"Name\":\"null\",\"Parameters\":[\"35.0\",\"40.0\",\"45.0\",\"50.0\"]}]},\"Integer\":\"1\",\"Boolean\":\"false\"}],\"Outputs\" : [{\"Name\":\"Sugeno out1\",\"Output\":{\"Type\":\"S\",\"Name\":\"Sugeno out1\",\"Functions\":[{\"Name\" : \"Function 1 Name\",\"Value\" : \"4.0\"},{\"Name\" : \"Function 1 Name\",\"Value\" : \"8.0\"},{\"Name\" : \"Function 2 Name\",\"Value\" : \"10.0\"}]},\"Integer\":\"2\",\"Boolean\":\"false\"},{\"Name\":\"Sugeno out2\",\"Output\":{\"Type\":\"S\",\"Name\":\"Sugeno out2\",\"Functions\":[{\"Name\" : \"Function 1 Name out2\",\"Value\" : \"10.0\"},{\"Name\" : \"Function 2 Name out2\",\"Value\" : \"20.0\"}]},\"Integer\":\"0\",\"Boolean\":\"false\"}]},{\"Connection Method\" : \"AND\",\"Inputs\" : [{\"Name\":\"A\",\"FuzzyVariable\":{\"Name\":\"A\",\"Start\":\"10.0\",\"End\":\"100.0\",\"Membership Functions\":[{\"Type\":\"Triangle\",\"Name\":\"null\",\"Parameters\":[\"10.0\",\"30.0\",\"40.0\"]},{\"Type\":\"Trapezoid\",\"Name\":\"null\",\"Parameters\":[\"30.0\",\"60.0\",\"80.0\",\"100.0\"]}]},\"Integer\":\"1\",\"Boolean\":\"true\"},{\"Name\":\"B\",\"FuzzyVariable\":{\"Name\":\"B\",\"Start\":\"30.0\",\"End\":\"50.0\",\"Membership Functions\":[{\"Type\":\"Triangle\",\"Name\":\"null\",\"Parameters\":[\"30.0\",\"35.0\",\"40.0\"]},{\"Type\":\"Trapezoid\",\"Name\":\"null\",\"Parameters\":[\"35.0\",\"40.0\",\"45.0\",\"50.0\"]}]},\"Integer\":\"0\",\"Boolean\":\"false\"}],\"Outputs\" : [{\"Name\":\"Sugeno out1\",\"Output\":{\"Type\":\"S\",\"Name\":\"Sugeno out1\",\"Functions\":[{\"Name\" : \"Function 1 Name\",\"Value\" : \"4.0\"},{\"Name\" : \"Function 1 Name\",\"Value\" : \"8.0\"},{\"Name\" : \"Function 2 Name\",\"Value\" : \"10.0\"}]},\"Integer\":\"2\",\"Boolean\":\"false\"},{\"Name\":\"Sugeno out2\",\"Output\":{\"Type\":\"S\",\"Name\":\"Sugeno out2\",\"Functions\":[{\"Name\" : \"Function 1 Name out2\",\"Value\" : \"10.0\"},{\"Name\" : \"Function 2 Name out2\",\"Value\" : \"20.0\"}]},\"Integer\":\"1\",\"Boolean\":\"false\"}]}]}]";
      final int result =
          await platformLoad.invokeMethod('load', {"json": json});
    } on PlatformException catch (e) {
      print(e);
    }
  }

  static Future<List<FuzzySystem>> get() async {
    List<dynamic>? systems = [];
    try {
      systems = await platformGetSystems.invokeListMethod('get_systems');
    } on PlatformException catch (e) {
      print(e);
    }

    List<String> list = systems!.cast<String>();
    List<FuzzySystem> result = [];
    for (int i = 0; i < list.length; i++) {
      result.add(
        FuzzySystem(
          index: i,
          name: list[i],
        ),
      );
    }

    return result;
  }

  static Future<void> add() async {
    try {
      final int result = await platformAddSystems.invokeMethod('add_systems');
    } on PlatformException catch (e) {
      print(e);
    }
  }

  static Future<void> deleteSystem(int index) async {
    try {
      final int result = await platformDeleteSystem
          .invokeMethod('delete_system', {"index": index});
    } on PlatformException catch (e) {
      print(e);
    }
  }

  static Future<List<FuzzyInput>> getSystemInputs(int index) async {
    List<dynamic>? inputs = [];
    try {
      inputs = await platformGetSystemInputs
          .invokeListMethod('get_system_inputs', {"index": index});
    } on PlatformException catch (e) {
      print(e);
    }
    List<String> list = inputs!.cast<String>();
    List<FuzzyInput> result = [];
    for (int i = 0; i < list.length; i++) {
      result.add(
        FuzzyInput(index: i, name: list[i], indexOfSystem: index),
      );
    }
    return result;
  }
}
