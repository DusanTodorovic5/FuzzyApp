import 'package:flutter/material.dart';

import 'adapters/fuzzy_plugin.dart';
import 'classes/fuzzy_system.dart';
import 'classes/fuzzy_systems.dart';
import 'pages/fuzzy_system_page.dart';
import 'widgets/system_slider.dart';

class MainPage extends StatefulWidget {
  MainPage({Key? key, required this.title}) : super(key: key);

  final String title;

  FuzzySystems fuzzySystems = FuzzySystems();

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  void nextPage(FuzzySystem system) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => FuzzySystemPage(
          title: system.name,
          index: system.index,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Center(
          child: Text(
            "Fuzzy System App",
          ),
        ),
      ),
      body: Column(
        children: [
          const Text(
            "My Fuzzy Systems",
            style: TextStyle(
              fontSize: 30,
            ),
          ),
          FutureBuilder<List<FuzzySystem>>(
            future: widget.fuzzySystems.systems,
            builder: (context, snapshot) {
              if (snapshot.hasError) {
                return Center(
                  child: Text(snapshot.error.toString()),
                );
              } else if (snapshot.hasData) {
                return Expanded(
                  child: ListView(
                    children: snapshot.data!
                        .map((e) {
                          return FuzzySystemSlider(
                            system: e,
                            myFunction: nextPage,
                          );
                        })
                        .toList()
                        .cast<Widget>(),
                  ),
                );
              } else {
                return const Center(
                  child: CircularProgressIndicator(),
                );
              }
            },
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          FuzzyPlugin.add().then((value) {
            setState(() {
              widget.fuzzySystems.refresh();
            });
          });
        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
