import 'dart:io';

import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter_chat_app_test/message.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'main.dart';

void main() {
  runApp(home());
}


class home extends StatefulWidget {
  @override
  _homeState createState() => _homeState();
}

class _homeState extends State<home> {

  List users = new List();
  List ids = new List();

  FirebaseAuth auth = FirebaseAuth.instance;
  User auth_user;

  CollectionReference all_users = FirebaseFirestore.instance.collection("all users");
  CollectionReference all_messages = FirebaseFirestore.instance.collection("all messages");
  String channel_id = "";
  String user_id = "";
  String partner_id = "";
  String id="";
  String name="";


  void initState() {
    super.initState();
    print("started init");
    print("starting list_data");
    list_data();
    print("list done");

      print("starting messagedsssss");
      // Navigator.push(context, MaterialPageRoute(builder: (context) =>
      //     message(channel_id: "T6XOxGP27TR76luQjk2IDO0y72h1-ZaDJqAsZKlffqBf2iy7ozvAueZX2",partner_id: "ZaDJqAsZKlffqBf2iy7ozvAueZX2",)));

  }
  void list_data() async {
    // Firebase.initializeApp();
    auth_user = auth.currentUser;
    user_id = await auth_user.uid;
    print("user id " + user_id);
    // print("user hashcode " + user_id.hashCode.toString());


    all_users.get().then((value) {
      print(value.size);
      for (int i = 0; i < value.size; i++) {

  id = value.docs.elementAt(i).get("id");
  name = value.docs.elementAt(i).get("name");
  if ((id) == user_id) {
    print("userid found");
  }
  else {
    if(mounted) {
      setState(() {
        users.add(name);
        ids.add(id);
        print(name);
        print(id);
      });
    }else{return CircularProgressIndicator();}
print(users);
  }



      }
      String date = DateTime.now().day.toString() + ":" + DateTime.now().month.toString() + ":" + DateTime.now().year.toString();
      // String hash = time.hashCode.toString();
      print("time : " + date);
    });

  }

  void channelid(int index)  {
    print(ids[index]);
    partner_id = ids[index];
    print(user_id);
    channel_id = "$user_id" + "-" + "$partner_id";
    if (user_id.hashCode <= partner_id.hashCode) {
      channel_id = '$user_id-$partner_id';
    } else {
      channel_id = '$partner_id-$user_id';
    }

    print(channel_id);


//
//   all_messages.get().then((value) {
//
//     print("in value");
//     // print(value.docs.contains("id").toString());
// // for(int i=0;i<value.docs.length;i++){
// //   print("in forloop value");
// //   print(value.docs.elementAt(i).id);
// //   if(value.docs.elementAt(i).id == channel_id){
// //     print("channel id exists");
// //   }
// //   else{
// //     all_messages.doc(channel_id).collection("time").doc(DateTime.now().microsecondsSinceEpoch.toString()).set({
// //       "channel id":channel_id,
// //       "from":user_id,
// //       "to":partner_id,
// //       "time":"",
// //       "content":""
// //     });
// //   }
// // }
//
//   });
    // await all_messages.doc(channel_id).get().then((value) {
    //   if(value["channel"].notnull){
    //     print("channel id exists");
    //   }
    //   else{
    //     all_messages.doc(user_id).collection("chat id").doc(channel_id).set({"id":channel_id,});
    //   }}  );


  }

  @override
  Widget build(BuildContext context) {
    Firebase.initializeApp();
    FirebaseAuth auth = FirebaseAuth.instance;
    User user = auth.currentUser;

    return MaterialApp(

      home: Scaffold(
        resizeToAvoidBottomInset: false,
        resizeToAvoidBottomPadding: false,

        appBar: AppBar(
          backgroundColor: Colors.lightGreen,
          leading: IconButton(icon: Icon(Icons.keyboard_backspace_rounded),
            onPressed: () {
              print(user);
              auth.signOut();
              Navigator.push(context, MaterialPageRoute(builder: (BuildContext context) { return firebaseinstance(); }));


              print("logout");
            },
          ),
          title: Text("chat"),

        ),
        body: ListView.builder(
          itemCount: users.length,
          itemBuilder: (BuildContext context, int index) {
            return ListTile(
              title: Text(users[index]),
              onTap: () {
                print(users[index]);
                channelid(index);
                Navigator.push(context, MaterialPageRoute(builder: (context) =>
                    message(channel_id: channel_id,partner_id: partner_id,))); //person: "hii ${users[index]}") )
              },
            );
          },
        ),
      ),
    );
  }
}
