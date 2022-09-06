import 'dart:async';
import 'dart:ui';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/services.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';

void main() {

  runApp(message(
    channel_id: null,
    partner_id: null,
  ));
}

// class mess_check extends StatelessWidget {
//   var channel_id;
//   var partner_id;
//   int leng;
//
//   mess_check({Key key, @required this.channel_id, @required this.partner_id})
//       : super(key: key);
//
//   @override
//   Widget build(BuildContext context) {
//     return Container();
//   }
// }

List mess_from = new List();

List mess_to = new List();
List all = new List();

List from_to = new List();
List to_from = new List();
List cont = new List();
final _scro=ScrollController();
class message extends StatefulWidget {
  var channel_id;

  var partner_id;

  message({Key key, @required this.channel_id, @required this.partner_id})
      : super(key: key);

  @override
  _messageState createState() => _messageState(channel_id, partner_id);
}
// ScrollController controller = new ScrollController();
class _messageState extends State<message> {

  var channel_id;
  int leng;
  var partner_id;
  String name = "";
  String from = "";
  String to = "";
  String userdata = "";
  String _message = '';

  _messageState(this.channel_id, this.partner_id);

  TextEditingController textmess = new TextEditingController();

  // _messageState({Key key,@required this.channel_id}):super(key:key);

  FirebaseAuth auth = FirebaseAuth.instance;
  User user;
  String uid;
  CollectionReference allmessage, allusers;

  String content = "null";
  Map<String, dynamic> something = {
    // "asdas":"asd"
  };

  void initState() {
    super.initState();
    print("IN MESSS");
    after_init();
    // blah();
// print("TOKEN   "+fire.getToken().toString());
// fire.getToken().then((value) => print("TOOK "+value));
    get();
  }


  void get() {
    print("inget");
    Firebase.initializeApp();
    FirebaseFirestore.instance.collection("all messages").doc(channel_id)
        .collection("time").doc().snapshots()
        .listen((event) {
      print("SNAPSHOTTT "+event.get("content"));
    });
    // snapshots().forEach((element) {
    //   print("SNAPSHOTTT"+element.docs);
    // });
    print("DONE GET");
  }

  // void getMessage(){
  //   fire.configure(
  //       onMessage: (Map<String, dynamic> message) async {
  //         print('on message $message');
  //         setState(() => _message = message["notification"]["title"]);
  //       }, onResume: (Map<String, dynamic> message) async {
  //     print('on resume $message');
  //     setState(() => _message = message["notification"]["title"]);
  //   }, onLaunch: (Map<String, dynamic> message) async {
  //     print('on launch $message');
  //     setState(() => _message = message["notification"]["title"]);
  //   });
  // }
  void after_init() async {
    Firebase.initializeApp();

    print("channel id:" + channel_id);
    print("partner id" + partner_id);
    user = auth.currentUser;
    uid = user.uid;
    allusers = FirebaseFirestore.instance.collection("all users");
    allmessage = FirebaseFirestore.instance.collection("all messages");

    print("BEFORE usersssss");
    allusers.doc(partner_id).get().then((value) {
      print("in userssssss");
      setState(() {
        name = value.get("name").toString();
        print(name);
      });
    });
    print("starting before a=messs");
    print(channel_id);
//TODO:error next to this
    allmessage.doc(channel_id).get().then((value) {
      print("in allllllmessss");

      // print(value.data());
      // print("in dates");

      if (value.data() == null) {
        print("IN IF");
        first_time();
      } else {
        allmessage
            .doc(channel_id)
            .collection("time")
            .orderBy("time")
            .get()
            .then((value) {
          setState(() {
            leng = value.docs.length;
          });
          print("length" + leng.toString());
          for (int i = 0; i < value.docs.length; i++) {
            print("IN FOR init");
            // print(value.docs.elementAt(i).get("content") );
            from = value.docs.elementAt(i).get("from").toString();

// print(from_to);
            to = value.docs.elementAt(i).get("to").toString();

            // print("TOOO"+to);
            userdata = value.docs.elementAt(i).get("content").toString();

            setState(() {
              from_to.add(from);
              to_from.add(to);
              cont.add(userdata);
            });
          }
        });
        print(from_to);
      }
    });
    // controller.animateTo(19, duration: Duration(seconds: 1), curve:  Curves.fastOutSlowIn);
  }

  // void blah(){
  //   print("IN BLANN");
  //   CollectionReference ref =FirebaseFirestore.instance.collection("all messages").doc(channel_id).collection("time");
  //   StreamBuilder(builder: (BuildContext context, AsyncSnapshot<dynamic> snapshot) {
  //     if(snapshot.hasError){
  //       CircularProgressIndicator();
  //     }
  //     // stream:,
  //     return
  //   },);
  //   print("DONW BLAH");
  // }

  void first_time() async {
    print("id doesnot exist"+channel_id);
    await allmessage.doc(channel_id).set({"channel id": channel_id});
    print("idset");
    allmessage
        .doc(channel_id)
        .collection("time")
        .doc(DateTime
        .now()
        .microsecondsSinceEpoch
        .toString())
        .set({
      "content": "hey welcome",
      "from": uid,
      "to": partner_id,
      "time": DateTime.now()
    });
  }

  void sendmessage() {
    allmessage
        .doc(channel_id)
        .collection("time")
        .doc(DateTime
        .now()
        .microsecondsSinceEpoch
        .toString())
        .set({
      "content": textmess.text.toString(),
      "from": uid,
      "to": partner_id,
      "time": DateTime.now()
    });
    print("sent message ");
    setState(() {
      cont.add(textmess.text.toString());
      leng = cont.length;
      textmess.clear();
    });
  }

  _check(String mess_from, String mess_to, String conten) {
    print("in check");
    print("mess from " + mess_from);
    print("mess to " + mess_to);
    print("uid " + uid);
    if (mess_from == uid) {
      print("in if");

      return Container(
        alignment: Alignment.topRight,
        child: Text(
          conten,
          style: TextStyle(fontSize: 20),
        ),
      );
    } else {
      print("in else");
      return Container(
        alignment: Alignment.topLeft,
        child: Text(
          conten,
          style: TextStyle(
            fontSize: 20,
          ),
        ),
      );
    }
  }

  _streamcheck() {
    // Stream ttt = ;
    StreamBuilder(
      stream: allmessage.snapshots(),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.done) {
          print("dddddd" + snapshot.data.data);
        }
        if (snapshot.hasError) {
          print("error");
        }
        if (snapshot.hasData) {
          print("has something");
        }
        return Text("the data" + snapshot.data.data.toString());
      },
    );
  }

  Widget listmess(DocumentSnapshot snapshot) {
    String aa = snapshot["content"];
    // notifica(aa);
    if (snapshot["from"] == uid) {
      // notifica(snapshot["content"]);

      return Container(
        alignment: Alignment.topRight,
        child: Padding(
          padding: const EdgeInsets.all(5.0),
          child: Container(
            // decoration: BoxDecoration(borderRadius: BorderRadius.circular(20),color: Colors.green[100]),
            decoration: BoxDecoration(borderRadius: BorderRadius.only(
                topRight: Radius.circular(20),
                topLeft: Radius.circular(20),
                bottomLeft: Radius.circular(20)), color: Colors.green[100]),

            // color: Colors.grey,
            width: MediaQuery
                .of(context)
                .size
                .width * 0.6,
            child: Padding(

              padding: const EdgeInsets.all(10.0),
              child: Container(child: Text(snapshot["content"],
                  style: TextStyle(fontSize: 20, color: Colors.black))),
            ),
          ),
        ),
      );
    } else {
      // notifica(snapshot["content"]);
      return Container(

        alignment: Alignment.topLeft,
        child: Padding(
          padding: const EdgeInsets.all(5.0),
          child: Container(
            // decoration: BoxDecoration(borderRadius: BorderRadius.circular(20),color: Colors.blueGrey[100]),
            decoration: BoxDecoration(borderRadius: BorderRadius.only(
                topRight: Radius.circular(20),
                topLeft: Radius.circular(20),
                bottomRight: Radius.circular(20)), color: Colors.blueGrey[100]),

            // color: Colors.grey,
            width: MediaQuery
                .of(context)
                .size
                .width * 0.6,
            child: Padding(

              padding: const EdgeInsets.all(10.0),
              child: Container(child: Text(snapshot["content"],
                style: TextStyle(fontSize: 20, color: Colors.black),),),
            ),
          ),
        ),
      );
    }
  }

// Widget somthitn(){
//   return Flexible(
//     child: StreamBuilder(
//       stream: allmessage.doc(channel_id).collection("time").snapshots(),
//       builder: ( context,  snapshot) {
//         if(snapshot.connectionState == ConnectionState.done){print("dddddd"+snapshot.data.data);}
//         if(snapshot.hasError){print("error");}
//         if(snapshot.hasData){print("has something");}
//         List test=new List();
//         test.addAll(snapshot.data.documents);
//         print("test "+test.toString());
//         return ListView.builder(
//           itemCount: snapshot.data.documents.length,
//           itemBuilder: (BuildContext context, int index) =>listmess(index, snapshot.data.documents[index]),);
//       },),
//   );
// }


//   void notifica(String Text_MESSAGE) {
//     print("IN NOTIFICATION");
//     FlutterLocalNotificationsPlugin flip = new FlutterLocalNotificationsPlugin();
// // var android = new AndroidInitializationSettings('@mipmap/ic_launcher');
// // var IOS = new IOSInitializationSettings();
//     var settings = new InitializationSettings(
//         android: new AndroidInitializationSettings('@mipmap/ic_launcher'),
//         iOS: new IOSInitializationSettings());
//     flip.initialize(settings);
//     show_notification(flip, Text_MESSAGE);
//     print("EXITED NOTIFICATION");
//     Future.value(true);
//   }
//
//   Future show_notification(flip, String messagessss) async {
//     print("ENTERED SHOW NOTIFICATION");
//     var androidplatform = new AndroidNotificationDetails(
//         "channelId", "1", "something");
//     var iosplatform = new IOSNotificationDetails();
//     var platform = new NotificationDetails(
//         android: androidplatform, iOS: iosplatform);
//     await flip.show(0, 'NOTIFICATION TEST',
//         messagessss,
//         platform, payload: 'Default_Sound'
//     );
//     print("EXITED SHOW NOTIFICATION");
//   }


  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      theme: ThemeData.dark(),
      home: SafeArea(
        child: Scaffold(
          resizeToAvoidBottomInset: false,
          // resizeToAvoidBottomPadding: false,

          appBar: AppBar(
            backgroundColor: Colors.amber,
            title: Text(name, style: TextStyle(color: Colors.black),),
            leading: IconButton(
              icon: Icon(Icons.arrow_back, color: Colors.black,),
              onPressed: () {
                // auth.signOut();
                setState(() {
                  cont.clear();
                  from_to.clear();
                  to_from.clear();
                  Navigator.pop(context);
                });
              },
            ),
          ),
          body: Column(
            children: [
              Flexible(
                child: StreamBuilder(

                  stream: FirebaseFirestore.instance
                      .collection("all messages")
                      .doc(channel_id)
                      .collection("time")
                      .snapshots(),
                  builder: (context, snapshot) {
                    if (snapshot.hasError) {
                      print("error");
                    }
                    if (snapshot.data == null) {
                      return Center(child: CircularProgressIndicator());
                    }
                    // if(snapshot.hasData){print("has something");}
                    // List test=new List();
                    // test.addAll(snapshot.data.documents);
                    // print("test "+test.toString());


                    Timer(
                      Duration(seconds: 0),
                          () => _scro.jumpTo(_scro.position.maxScrollExtent),
                    );
                    return ListView.builder(

                        controller: _scro,
                        reverse: false,
                        itemCount: snapshot.data.docs.length,
                          itemBuilder: (BuildContext context, int index) {
                            print("data"+snapshot.data.docs[index].toString());
                            return listmess(snapshot.data.docs[index]);
                          }
                    );
                  },
                ),

              ),
              Container(
                // color: Colors.white,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(50), color: Colors.amber),
                child: Row(
                  children: [
                    Flexible(
                      child: Container(
                        // decoration: BoxDecoration(
                        //     color: Colors.green,
                        //     borderRadius: BorderRadius.circular(50)),
                        child: TextFormField(
                          controller: textmess,
                          decoration: InputDecoration(
                            hintText: "    Enter Text",
                            hintStyle: TextStyle(color: Colors.black),
                            labelStyle: TextStyle(color: Colors.black),

                            focusColor: Colors.black,
                            // border: OutlineInputBorder(
                            //   borderRadius: BorderRadius.circular(50),
                            // ),
                          ),
                          cursorColor: Colors.black,
                        ),
                      ),
                    ),
                    // Container(
                    //   decoration: BoxDecoration(borderRadius: BorderRadius.circular(100)),
                    //   child: FlatButton(
                    //     onPressed: () async {
                    //       setState(() {
                    //         sendmessage();
                    //       });
                    //
                    //       // print(await messi);
                    //       // print(something);
                    //     },
                    //     child: Text("+"),
                    //     // color: Colors.red,
                    //   ),
                    // ),
                    IconButton(icon: Icon(
                      Icons.keyboard_arrow_right_rounded, color: Colors.black,),
                      onPressed: () {
                        setState(() {
                          sendmessage();
                          // notifica();
                        });
                      },

                    )
                  ],
                ),
              ),
            ],
          ),

          // Stack(
          //   children: [
          //
          //     ListView.builder(
          //       scrollDirection: Axis.vertical,
          //       shrinkWrap: true,
          //       reverse:true,
          //       itemCount: mess_from.length+mess_to.length,
          //       itemBuilder: (BuildContext context, int index) {
          //         return Column(
          //           children: [
          //
          //             Container(
          //               alignment:Alignment.topRight,
          //               child:Text(mess_from[index]),
          //             ),Container(
          //               alignment:Alignment.topLeft,
          //               child:Text(mess_to[index]),
          //             ),
          //           ],
          //         );
          //       },
          //     ),
          //     Align(
          //       alignment: Alignment.bottomCenter,
          //       child: Container(
          //         color: Colors.white,
          //         child: Row(
          //           children: [
          //             Flexible(
          //               child: Container(
          //                 decoration: BoxDecoration(
          //                     color: Colors.green,
          //                     borderRadius: BorderRadius.circular(50)),
          //                 child: TextFormField(
          //                   controller: textmess,
          //                   decoration: InputDecoration(
          //                     focusColor: Colors.black,
          //                     border: OutlineInputBorder(
          //                       borderRadius: BorderRadius.circular(50),
          //                     ),
          //                   ),
          //                   cursorColor: Colors.black,
          //                 ),
          //               ),
          //             ),
          //             FlatButton(
          //               onPressed: () async {
          //                  // sendmessage();
          //                 print(await messi);
          //                 print(something);
          //               },
          //               child: Text("+"),
          //               color: Colors.red,
          //             )
          //           ],
          //         ),
          //       ),
          //     ),
          //   ],
          // ),
          // Column(
          //   children: [
          //     Container(alignment:Alignment.topRight,child: Text("afssdf"),),
          //     Container(alignment:Alignment.topLeft,child: Text("afssdf"),),
          //
          //     Container(alignment:Alignment.topRight,child: Text("afssdf"),),
          //     Container(alignment:Alignment.topLeft,child: Text("afssdf"),),
          //   ],
          // )
          // ListView.builder(
          //   itemCount: mess_from.length,
          //   itemBuilder: (BuildContext context, int index) {
          //     return Text(mess_from[index]);
          //   },
          // )
        ),
      ),
    );
  }

}







// void callbackDispatcher() {
//   int count = 0;
//   Timer(
//     Duration(seconds: 2),
//
//         () => print(count++),
//   );
// }
//   void backk(){
//      const MethodChannel _channel = const MethodChannel("channel-name");
//
//     Future<void> initialize(final Function callbackDispatcher) async {
//       final callback = PluginUtilities.getCallbackHandle(callbackDispatcher);
//       await _channel.invokeMethod('initialize', callback.toRawHandle());
//     }
//   }




// class from_mess extends StatelessWidget {
//   String mess;
//
//    from_mess(String mess);
//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       home: Scaffold(
//         body: ListView.builder(
//           scrollDirection: Axis.vertical,
//           shrinkWrap: true,
//           reverse:true,
//           itemCount: mess_from.length + mess_to.length,
//           itemBuilder: (BuildContext context, int index) {
//             return Align(
//               alignment: Alignment.topRight,
//               child: Text("From " + mess),
//             );
//           },
//         ),
//       ),
//     );
//   }
// }
//
// class to_mess extends StatelessWidget {
//   String mess;
//
//   to_mess(String mess);
//
//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       home: Scaffold(
//         body: ListView.builder(
//           scrollDirection: Axis.vertical,
//           shrinkWrap: true,
//           reverse: true,
//           itemCount: mess_to.length,
//           itemBuilder: (BuildContext context, int index) {
//             return Align(
//               alignment: Alignment.topRight,
//               child: Text("TO " + mess),
//             );
//           },
//         ),
//       ),
//     );
//   }
// }
