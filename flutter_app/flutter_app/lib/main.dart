import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
     title: 'Flutter Demo',
      home:te()

    );
  }
}

class te extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<te> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.red,
      appBar: AppBar(
      title: Text("testing",
        style: TextStyle(
            backgroundColor: Colors.blueGrey,
            fontSize: 70
        ),
      ),
      ),

    );
  }
}
