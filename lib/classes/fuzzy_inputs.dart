import '../adapters/fuzzy_plugin.dart';
import 'fuzzy_input.dart';

class FuzzyInputs {
  FuzzyInputs({required this.index}) {
    FuzzyPlugin.load().then((value) => refresh());
  }

  late Future<List<FuzzyInput>> inputs = FuzzyPlugin.getSystemInputs(index);

  void refresh() {
    inputs = FuzzyPlugin.getSystemInputs(index);
  }

  int index;
}
