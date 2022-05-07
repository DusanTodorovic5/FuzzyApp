import 'package:flutter/material.dart';
import 'package:flutter_slidable/flutter_slidable.dart';

import '../classes/fuzzy_system.dart';

class FuzzySystemSlider extends StatelessWidget {
  const FuzzySystemSlider(
      {Key? key, required this.system, required this.myFunction})
      : super(key: key);

  final FuzzySystem system;
  final Function myFunction;

  @override
  Widget build(BuildContext context) {
    return Slidable(
      key: const ValueKey(0),
      startActionPane: ActionPane(
        motion: ScrollMotion(),
        children: [
          SlidableAction(
            flex: 2,
            onPressed: (BuildContext context) {},
            backgroundColor: Colors.red,
            foregroundColor: Colors.white,
            icon: Icons.delete,
            label: 'Delete',
          ),
        ],
      ),
      child: GestureDetector(
        child: ListTile(
          title: Text(system.name),
          onTap: () {
            myFunction(system);
          },
        ),
        onTap: () {
          myFunction(system);
        },
      ),
    );
  }
}
