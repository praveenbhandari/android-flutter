// ignore_for_file: deprecated_member_use

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_chat_app_test/home.dart';

void main() {
  runApp(details());
}

class check_details extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    print("in build ..... ");
    Firebase.initializeApp();
    FirebaseAuth auth=FirebaseAuth.instance;
    User user=auth.currentUser;

    if (user.displayName != null) {
      print("IN check ");
      print("going to home");
      return home();
    } else {
      print("going to details");
      return details();
    }
  }
}

class details extends StatefulWidget {
  @override
  _detailsState createState() => _detailsState();
}

class _detailsState extends State<details> {
  TextEditingController name = new TextEditingController();
  TextEditingController phone = new TextEditingController();
  FirebaseAuth auth;
  User user;
  CollectionReference firestore =
      FirebaseFirestore.instance.collection("all users");

  void initState() {
    super.initState();
    // check();
    Firebase.initializeApp();
    print("in init");
  }

//   void check() {
//     auth = FirebaseAuth.instance;
//     user = auth.currentUser;
//     print("in details init ");
//     if (user.displayName != null) {
//       print("IN IF ");
//
//       Navigator.push(context,
//           MaterialPageRoute(builder: (BuildContext context) => home()));
//     }
//
// // print("name="+user.displayName);
//
// //   if(user.displayName != null){
// //     print("NAME IS NOT NULLLLL");
// //     setState(() {
// //       print("before navifaotr");
// //       // home();
// //       Navigator.push(context, MaterialPageRoute(builder: (BuildContext context) =>home()  ));
// //       print("after navifaotr");
// //     });
// //
// //   }
//   }

  void update() async {
    print("inupdate");
    Firebase.initializeApp();
    FirebaseAuth auth=FirebaseAuth.instance;
    user =  auth.currentUser;
    print("USER:"+user.toString());
    print(user.uid);
    print(name.text);
    user.updateProfile(displayName: name.text);
    firestore.doc(user.uid).update({
      "name": name.text,
      "phone": phone.text
    }).onError((error, stackTrace) => print("eee:"+error.toString()) );  //TODO: need to verify the number with user.linkNumber();
    print(user.displayName);
    print("updated");
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        resizeToAvoidBottomInset: false,
        // resizeToAvoidBottomPadding: false,

        appBar: AppBar(backgroundColor: Colors.lightGreen,),
        body: SingleChildScrollView(

          child: Stack(
            children: [
              Column(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                SizedBox(height: 100,),
                Padding(
                  padding: const EdgeInsets.all(20),
                  child: TextFormField(
                    decoration: InputDecoration(labelText: "Name",border: OutlineInputBorder(borderRadius: BorderRadius.circular(30), )),
                    controller: name,
                  ),
                ),
                SizedBox(height: 30,),
                Padding(
                  padding: const EdgeInsets.all(20),
                  child: TextFormField(
                    decoration: InputDecoration(labelText: "Phone No",border: OutlineInputBorder(borderRadius: BorderRadius.circular(30), )),
                    controller: phone,
                  ),
                ),
                SizedBox(height: 80,),
                Container(
                      width: 300,
                  height: 50,

                  child: FlatButton(
                    color: Colors.lightGreen,
                    onPressed: () async {
                      // print(name.text);
                      // print(phone.text);
                      update();
                      print("after update");
                      print(user.phoneNumber);
                      print(user.displayName);
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (BuildContext context) => home()));
                    },
                    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(30 ) ),
                    child: Text("Submit",style: TextStyle(fontSize: 30,color: Colors.black87),),
                  ),
                )
              ],
            ),
            ],
          ),
        ),
      ),
    );
  }
}
