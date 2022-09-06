import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'listview.dart';

void main(){
  runApp(login());
}
class login extends StatefulWidget {
  @override
  _loginState createState() => _loginState();
}

class _loginState extends State<login> {
  TextEditingController emailid=new TextEditingController();
  TextEditingController password = new TextEditingController();
  String asas;


  Future<void> anonlogin() async {
    await Firebase.initializeApp();
    final FirebaseAuth auth = FirebaseAuth.instance;
    try{
      await auth.signInAnonymously().whenComplete(() {
        print("loggedin");
        afterlogin();
      });
    }catch(e){
      print("Error  ");
    }

  }
  Future<void> emailsignup() async {
    await Firebase.initializeApp();
    final FirebaseAuth auth = FirebaseAuth.instance;
    try{
      // print(emailid.text);
      // print(password.text);
       await auth.createUserWithEmailAndPassword(email: emailid.text, password: password.text).whenComplete(() {
      print("loggedin");
       afterlogin();
      });

    }catch(e){
      print("Error  ");
    }

  }
  Future<void> loginid() async {
    await Firebase.initializeApp();
    final FirebaseAuth auth = FirebaseAuth.instance;
    try{
      await auth.signInWithEmailAndPassword(email: emailid.text, password: password.text).whenComplete(() {
        Navigator.push(context, MaterialPageRoute( builder: (BuildContext context) { return afterlogin() ;}));
        print("loggedin");
      });

    }catch(e){
      print("Error  ");
    }

  }




  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: SafeArea(
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Container(
                  child: Column(
                    children: [
                      Container(
                        width: 300,
                        child: TextFormField(
                          controller: emailid,

                          decoration: InputDecoration(border: OutlineInputBorder(),hintText: "EmailID"),
                        ),
                      ),
                      Container(
                        width: 300,
                        child: TextFormField(
                          controller: password,

                          decoration: InputDecoration(border: OutlineInputBorder(),hintText: "Password"),
                        ),
                      ),
                    ],
                  ),
                ),
              Container(

                child: Column(
                  children: [
                    Container(
                      width: 300,
                      child: FlatButton(
                        color: Colors.deepPurple,
                        onPressed: () {
                        //  print(emailid.value);
                        setState(() {
                          emailsignup();
                          // print(emailid.text);
                          // print(password.text);
                        });
                      }, child: Text("Email Signup",style: TextStyle( fontFamily: "Abel", fontSize: 30, color: Colors.white70),),),
                    ),
                    Container(
                      width: 300,
                      child: FlatButton(
                        color: Colors.deepPurple,
                        onPressed: () {
                          //  print(emailid.value);
                          setState(() {
                            anonlogin();
                            // print(emailid.text);
                            // print(password.text);
                          });
                        }, child: Text("Anonymous Login",style: TextStyle( fontFamily: "Abel", fontSize: 30, color: Colors.white70),),),
                    ),
                    Container(
                      width: 300,
                      child: FlatButton(
                        color: Colors.deepPurple,
                        onPressed: () {
                          //  print(emailid.value);
                          setState(() {
                            loginid();
                            //afterlogin();
                            // print(emailid.text);
                            // print(password.text);
                          });
                        }, child: Text("Login ",style: TextStyle( fontFamily: "Abel", fontSize: 30, color: Colors.white70),),),
                    ),

                  ],
                ),
              ),

              ],
            ),
          ),
        ),
      ),
    );
  }
}
class afterlogin extends StatefulWidget {

  @override
  _afterloginState createState() => _afterloginState();
}

class _afterloginState extends State<afterlogin> {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Text("LOGGED IN SUCCESSFULLY",style: TextStyle(
            color: Colors.deepPurple,fontSize: 50
        ),),
      ),
    );
  }
}

