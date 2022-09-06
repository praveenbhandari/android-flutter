import 'dart:math';

import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  int ran = 1;
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.blueGrey[900],
        appBar: AppBar(
          backgroundColor: Colors.yellow[900],
          title: Text(
            "Boss",
            style: TextStyle(fontSize: 20),
          ),
        ),
        body: Center(
          child: FlatButton(
            onPressed: () {
              print("pressed");
              setState(() {
                ran = Random().nextInt(5) + 1;
              });
            },
            child: Image.asset('images/ball$ran.png'),
          ),
        ),
      ),
    );
  }
}
