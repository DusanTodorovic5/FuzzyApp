import 'package:fuzzy_app/adapters/fuzzy_plugin.dart';
import 'package:fuzzy_app/classes/fuzzy_system.dart';

class FuzzySystems {
  FuzzySystems() {
    FuzzyPlugin.load().then((value) => refresh());
  }

  Future<List<FuzzySystem>> systems = FuzzyPlugin.get();

  void refresh() {
    systems = FuzzyPlugin.get();
  }
}
