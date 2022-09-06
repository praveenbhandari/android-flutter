import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';

void main() {
  runApp(firebase_test());
}

class fire extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: Firebase.initializeApp(),
      builder: (BuildContext context, AsyncSnapshot<dynamic> snapshot) {
        if (snapshot.hasError) {
          print("ERROR 1");
          return error();
        }
        if (snapshot.connectionState == ConnectionState.done) {
          print("Starting");
          return firebase_test();
        }
        print("return error");
        return MaterialApp(
          home: Scaffold(
            body: SafeArea(
                child: Text(
              " Loading & Loading & Loading ",
              style: TextStyle(
                color: Colors.deepPurple,
                fontSize: 20,
              ),
            )),
          ),
        );
      },
    );
  }
}

class error extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: SafeArea(
           child: CircularProgressIndicator(),
          // Text(
          //   " Firebase ERROR ",
          //   style: TextStyle(color: Colors.deepPurple, fontSize: 20),
          // ),
        ),
      ),
    );
  }
}

class firebase_test extends StatefulWidget {
  static var a;

  @override
  _firebase_testState createState() => _firebase_testState();
}

class _firebase_testState extends State<firebase_test> {
  bool issel = false;
  String data1 = "null";
  String data2 = "null";
  TextEditingController tst = new TextEditingController();
  String changewala;

  FirebaseFirestore firestore = FirebaseFirestore.instance;

  void firesend(String da) {
    CollectionReference users = FirebaseFirestore.instance.collection('flutter');
    if (da.isEmpty) {
      print("Enter something");
    } else {
      print("sent ${da}");
      if(issel==false){
        users.doc("test").update({"data": da}).whenComplete(() => print("sent data"));
      }
      else{
        users.doc("test").update({"data2": da}).whenComplete(() => print("sent data"));
      }

    }
    users.doc("test").get().then((value) {
      //print(value[]);
      setState(() {
        data1 = value['data'];
        data2 = value['data2'];
      });
    });
  }

  void onchangewalatext(String val) {
    setState(() {
      changewala = val;
      print(changewala);
    });
  }

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Firebase"),
          backgroundColor: Colors.deepPurple,
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: [

              Container(
                width: 300,
                height: 100,
                child: TextFormField(
                  controller: tst,
                  //  onChanged: onchangewalatext,
                  maxLength: 10,
                  decoration: InputDecoration(border: OutlineInputBorder()),
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                Container(
                  height: 80,

                  child: RotatedBox(
                    quarterTurns: 45,
                    child: Switch(
                        activeColor: Colors.lightGreenAccent,
                        inactiveTrackColor: Colors.deepPurple,
                        value: issel,
                        onChanged: (value) {
                          setState(() {
                            issel = value;
                            print(issel);
                          });
                        }),
                  ),
                ),
                Column(children: [
                  Text(data1,style: TextStyle(fontSize: 30),),
                  SizedBox(height: 50,),
                  Text(data2,style: TextStyle(fontSize: 30),),
                ],),
                  SizedBox(width: 50,)
              ],),


              FlatButton(
                color: Colors.deepPurple,
                child: Text(
                  "Submit ",
                  style: TextStyle(color: Colors.white, fontSize: 40),
                ),
                onPressed: () {
                  setState(() {
                    firesend(tst.text);

                    //print(tst.text);
                  });
                  //  print("q");
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}
