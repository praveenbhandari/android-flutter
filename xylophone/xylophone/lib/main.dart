import 'package:flutter/material.dart';
import 'package:toast/toast.dart';
import 'package:audioplayers/audio_cache.dart';

//import 'package:fluttertoast/fluttertoast.dart';
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.

  void tone(int number1) {
    final player = AudioCache();
    player.play('note$number1.wav');
  }

  Expanded buildKey({Color color, int num}) {
    return Expanded(
      child: FlatButton(
        onPressed: () {
          tone(num);
        // print("1");
        }, //child: Image.asset('as/dice1.png'),
        color: color, child: Text(""),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.black,
//        appBar: AppBar(
//          backgroundColor: Colors.yellow,
//          title: Text(
//            "Xylophone",
//            style: TextStyle(color: Colors.black),
//          ),
//        ),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            buildKey(color: Colors.deepPurple,num:1),
            buildKey( color: Colors.indigo,num:2),
            buildKey(color: Colors.blue,num:3),
            buildKey(color: Colors.green,num:4),
            buildKey(color: Colors.yellowAccent,num:5),
            buildKey(color: Colors.orange,num:6),
            buildKey(color: Colors.red,num:7),

//           Expanded(
//              child: FlatButton(
//                onPressed: () {
//                  tone(1);
//                  print("1");
//                  Toast.show("TEST",context,duration: Toast.LENGTH_LONG,gravity: Toast.BOTTOM);
//
//                }, //child: Image.asset('as/dice1.png'),
//                color: Colors.deepPurple, child: Text(""),
//              ),
//            ),
//            Expanded(
//              child: FlatButton(
//                onPressed: () {
//                  print("2");
//                  tone(2);
//                  Toast.show("TEST",context,duration: Toast.LENGTH_LONG,gravity: Toast.BOTTOM);
//                }, child: Text(""),
//                color: Colors.indigo, //child: Text("2"),
//              ),
//            ),
//            Expanded(
//              child: FlatButton(
//                onPressed: () {
//                  print("3");
//                  tone(3);
//                  Toast.show("TEST",context,duration: Toast.LENGTH_LONG,gravity: Toast.BOTTOM);
//
//                },
//                color: Colors.blue,
//                   child: Text(""),
//              ),
//            ),
//            Expanded(
//              child: FlatButton(
//                onPressed: () {
//                  print("4");
//                  tone(4);
//                }, child: Text(""),
//                color: Colors.green,
//              ),
//            ),
//            Expanded(
//              child: FlatButton(
//                onPressed: () {
//                  print("5");
//                  tone(5);
//                }, child: Text(""),
//                color: Colors.yellowAccent,
//              ),
//            ),
//            Expanded(
//              child: FlatButton(
//                onPressed: () {
//                  tone(6);
//                  print("6");
//                }, child: Text(""),
//                color: Colors.orange,
//              ),
//            ),
//            Expanded(
//              child: FlatButton(
//                onPressed: () {
//                  tone(7);
//                  print("7");
//                }, child: Text(""),
//                color: Colors.red,
//              ),
//            ),
          ],
        ),
      ),
    );
  }
}
