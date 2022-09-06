import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_auth/firebase_auth.dart';

import 'details.dart';
import 'home.dart';

void main() async {
  runApp(MaterialApp(
    home: Scaffold(
      body: firebaseinstance(),
    ),
  ));
}

class firebaseinstance extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: Firebase.initializeApp(),
      builder: (BuildContext context, AsyncSnapshot<dynamic> snapshot) {
        if (snapshot.hasError) {
          return MaterialApp(
            home: Scaffold(
              resizeToAvoidBottomInset: false,
              // resizeToAvoidBottomPadding: false,
              body: CircularProgressIndicator(),
            ),
          );
        }
        if (snapshot.connectionState == ConnectionState.done) {
          return auth_page();
        }

        return MaterialApp(
          home: Scaffold(
            body: CircularProgressIndicator(),
          ),
        );
      },
    );
  }
}

class auth_page extends StatefulWidget {
  @override
  _auth_pageState createState() => _auth_pageState();
}

class _auth_pageState extends State<auth_page> {
  String init = '/';
  TextEditingController email = new TextEditingController();
  TextEditingController pass = new TextEditingController();
  TextEditingController name = new TextEditingController();
  FirebaseAuth auth = FirebaseAuth.instance;
  User current_user;

  void initState() {
    super.initState();
    check();
  }

  void check() {
    current_user = auth.currentUser;
    if (current_user != null) {
      print("init " + current_user.toString());
      setState(() {
        init = "details";
      });
    }
  }

  void signin() {
    print("Signin With Email");
    auth
        .createUserWithEmailAndPassword(email: email.text, password: pass.text)
        .whenComplete(() {
      print("Signin Completed");
      FirebaseAuth auth = FirebaseAuth.instance;
      // User user;
      CollectionReference fire =
          FirebaseFirestore.instance.collection("all users");
      fire.doc(auth.currentUser.uid).set({"uid": auth.currentUser.uid, "name": "a", "phone": "1"}).whenComplete(() {
        Navigator.push(context,MaterialPageRoute(builder: (BuildContext context) => check_details()));
      }).onError((error, stackTrace) => print('ERROR'+error));

      // current_user = await auth.currentUser;
      // current_user.updateProfile(displayName: name.text);
      // firestore.doc(user.uid).update({"name":user.displayName,"phone no":phone.text});  //TODO: need to verify the number with user.linkNumber();
      // print(user.displayName);
    }).onError((error, stackTrace) {
      print("eeror: " + error.toString());
    });
  }

  void login() {
    auth
        .signInWithEmailAndPassword(email: email.text, password: pass.text)
        .then((value) {
      print("loggin");
      // current_user =  auth.currentUser;
      // current_user.updateProfile(displayName: name.text);
      // print(name.text);
      // firestore.doc(user.uid).update({"name":user.displayName,"phone no":phone.text});  //TODO: need to verify the number with user.linkNumber();
      // print(user.displayName);

      Navigator.push(
              context, MaterialPageRoute(builder: (context) => check_details()))
          .catchError((onError) => print("error" + onError));
      print("loggin");
    }).catchError((e) => print("error : " + e));
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: init,
      routes: {
        // 'home':(context) => home(),
        'details': (context) => check_details(),
      },
      home: Scaffold(
        // resizeToAvoidBottomPadding: false,
        resizeToAvoidBottomInset: false,
        body: SingleChildScrollView(
          child: Stack(
            // mainAxisAlignment: MainAxisAlignment.center,
            // //  crossAxisAlignment: CrossAxisAlignment.end,
            // fit: StackFit.loose,
            children: [
              // Container(
              //   // width: 350,
              //   child: TextFormField(
              //     decoration: InputDecoration(labelText: "Name"),
              //
              //     controller: name,
              //   ),
              // ),
              Column(
                children: [
                  SizedBox(
                    height: 150,
                  ),
                  Container(
                    // width: 350,

                    child: Padding(
                      padding: const EdgeInsets.all(20),
                      child: TextFormField(
                        decoration: InputDecoration(
                            labelText: "Email",
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(30),
                            )),
                        controller: email,
                      ),
                    ),
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  Container(
                    // width: 350,
                    child: Padding(
                      padding: const EdgeInsets.all(20),
                      child: TextFormField(
                        decoration: InputDecoration(
                            labelText: "Password",
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(30),
                            )),
                        obscureText: true,
                        controller: pass,
                      ),
                    ),
                  ),
                  SizedBox(
                    height: 200,
                  ),
                  Column(
                    children: [
                      Container(
                        // decoration: BoxDecoration(borderRadius:Border),
                        height: 50,
                        width: 300,
                        child: FlatButton(
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(30)),
                          color: Colors.lightGreen,
                          onPressed: () async {
                            print("clicked");
                            await signin();
                            // print("aomw"+email.text +pass.text );
                          },
                          child: Text(
                            "Sign in",
                            style: TextStyle(fontSize: 30),
                          ),
                        ),
                      ),
                      SizedBox(
                        height: 50,
                      ),
                      Container(
                        width: 300,
                        height: 50,
                        child: FlatButton(
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(30)),
                          color: Colors.lightGreen,
                          onPressed: () async {
                            print("clicked");
                            // print(name.text);
                            await login();
                            // print("aomw"+email.t
                          },
                          child: Text(
                            "Login",
                            style:
                                TextStyle(fontSize: 30, color: Colors.black87),
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
