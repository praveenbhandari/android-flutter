import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:list_english_words/list_english_words.dart';
import 'listview.dart';

import 'dart:math';

void main() {}

class words extends StatefulWidget {
  @override
  _wordsState createState() => _wordsState();
}

class _wordsState extends State<words> {
  String text1 = "null";

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("WORDS"),
          backgroundColor: Colors.deepPurple,
        ),
        body: Container(
          child: Center(
            child: Column(
              // crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                SizedBox(
                  height: 150,
                ),
                Text(
                  "$text1",
                  style: TextStyle(
                    fontFamily: "Abel",
                    fontSize: 80,
                  ),
                ),
                SizedBox(
                  height: 200,
                ),
                Column(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(10),
                      child: FlatButton(
                        color: Colors.deepPurple,
                        minWidth: 500,
                        onPressed: () {
                          setState(() {
                            var ran = new Random();
                            text1 = list_english_words[ran.nextInt(list_english_words.length)];
                          });
                        },
                        child: Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Text(
                            "Next Word",
                            style: TextStyle(
                                fontSize: 40,
                                color: Colors.white,
                                fontFamily: "Abel"),
                          ),
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(10),
                      child: FlatButton(
                        color: Colors.deepPurple,
                        minWidth: 500,
                        onPressed: () {
                          setState(() {
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => listview()));
                          });
                        },
                        child: Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Text(
                            "List View",
                            style: TextStyle(
                                fontSize: 40,
                                color: Colors.white,
                                fontFamily: "Abel"),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
