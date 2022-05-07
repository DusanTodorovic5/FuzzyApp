import 'package:flutter/material.dart';
import 'package:flutter_slidable/flutter_slidable.dart';
import 'package:fuzzy_app/classes/fuzzy_inputs.dart';

import '../classes/fuzzy_input.dart';

class FuzzySystemPage extends StatefulWidget {
  FuzzySystemPage({Key? key, required this.title, required this.index})
      : super(key: key);

  final String title;
  int index;

  late FuzzyInputs fuzzyInputs = FuzzyInputs(index: index);
  @override
  State<FuzzySystemPage> createState() => _FuzzySystemPageState();
}

class _FuzzySystemPageState extends State<FuzzySystemPage> {
  List lista = ["Dusan", "Aleksa", "Ime", "Dusan", "Aleksa", "Ime"];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Center(
          child: Text(
            widget.title,
          ),
        ),
      ),
      body: ListView(
        children: [
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Padding(
                padding: EdgeInsets.all(5.0),
                child: Text("Type:"),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 8.0, right: 8.0),
                child: DropdownButton<String>(
                    value: "Mamdani",
                    items: const [
                      DropdownMenuItem(
                        child: Text("Mamdani"),
                        value: "Mamdani",
                      ),
                      DropdownMenuItem(
                        child: Text("Sugeno"),
                        value: "Sugeno",
                      ),
                    ],
                    onChanged: (String? s) {
                      print(s);
                    }),
              ),
            ],
          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Padding(
                padding: EdgeInsets.all(5.0),
                child: Text("And Method:"),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 8.0, right: 8.0),
                child: DropdownButton<String>(
                    value: "Min",
                    items: const [
                      DropdownMenuItem(
                        child: Text("Min"),
                        value: "Min",
                      ),
                      DropdownMenuItem(
                        child: Text("Prod"),
                        value: "Prod",
                      ),
                    ],
                    onChanged: (String? s) {
                      print(s);
                    }),
              ),
            ],
          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Padding(
                padding: EdgeInsets.all(5.0),
                child: Text("Or Method:"),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 8.0, right: 8.0),
                child: DropdownButton<String>(
                    value: "Max",
                    items: const [
                      DropdownMenuItem(
                        value: "Max",
                        child: Text("Max"),
                      ),
                      DropdownMenuItem(
                        value: "Probor",
                        child: Text("Probor"),
                      ),
                    ],
                    onChanged: (String? s) {
                      print(s);
                    }),
              ),
            ],
          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Padding(
                padding: EdgeInsets.all(5.0),
                child: Text("Implication Method:"),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 8.0, right: 8.0),
                child: DropdownButton<String>(
                    value: "Clipping",
                    items: const [
                      DropdownMenuItem(
                        value: "Clipping",
                        child: Text("Clipping"),
                      ),
                      DropdownMenuItem(
                        value: "Scale",
                        child: Text("Scale"),
                      ),
                    ],
                    onChanged: (String? s) {
                      print(s);
                    }),
              ),
            ],
          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Padding(
                padding: EdgeInsets.all(5),
                child: Text(
                  "Aggregation Method:",
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 8.0, right: 8.0),
                child: DropdownButton<String>(
                    value: "Max",
                    items: const [
                      DropdownMenuItem(
                        value: "Max",
                        child: Text(
                          "Max",
                        ),
                      ),
                    ],
                    onChanged: (String? s) {
                      print(s);
                    }),
              ),
            ],
          ),
          const Text(
            "Inputs",
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 30,
            ),
          ),
          FutureBuilder<List<FuzzyInput>>(
            future: widget.fuzzyInputs.inputs,
            builder: (context, snapshot) {
              if (snapshot.hasError) {
                return Center(
                  child: Text(snapshot.error.toString()),
                );
              } else if (snapshot.hasData) {
                return Column(
                  children: snapshot.data!
                      .map((e) {
                        return MySlider(
                          title: e.name,
                          myFunction: () {},
                        );
                      })
                      .toList()
                      .cast<Widget>(),
                );
              } else {
                return const Center(
                  child: CircularProgressIndicator(),
                );
              }
            },
          ),
          MaterialButton(
            onPressed: () {},
            child: const Text("Add input"),
          ),
          const Text(
            "Outputs",
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 30,
            ),
          ),
          Column(
            children: lista.map((e) {
              return MySlider(
                title: e,
                myFunction: () {
                  setState(() {});
                },
              );
            }).toList(),
          ),
          MaterialButton(
            onPressed: () {
              setState(() {});
            },
            child: const Text("Add output"),
          ),
        ],
      ),
    );
  }
}

class MySlider extends StatelessWidget {
  const MySlider({Key? key, required this.title, required this.myFunction})
      : super(key: key);

  final String title;
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
          title: Text(title),
          onTap: () {
            myFunction(title);
          },
        ),
        onTap: () {
          myFunction(title);
        },
      ),
    );
  }
}
