//import 'dart:html';

import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.black26,
        body: SafeArea(child: txt(),),
      ),
    );
  }
}

class txt extends StatefulWidget {
  @override
  _txtState createState() => _txtState();
}

class _txtState extends State<txt> {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[
        Expanded(
          child: Text("question"),
        ),
      ],
    );
  }
}
