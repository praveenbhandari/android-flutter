import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app_test/firebase_test.dart';
import 'package:flutter_app_test/mongodtest.dart';
import 'package:flutter_app_test/mongolist.dart';
import 'package:flutter_app_test/todo.dart';
import 'package:flutter_app_test/words.dart';
import 'package:toast/toast.dart';
import 'firebase_authentication.dart';
import 'listview.dart';
import 'maptest.dart';
import 'notification_flutter.dart';


void main()  {

  runApp(
    MaterialApp(
      initialRoute: '/',
      routes: {
        '/list': (context) => listview(),
        '/words': (context) => words(),
        '/firebase': (context) => fires(),
        '/mongolist':(context) =>mongolist(),
        '/login':(context) => login(),
        '/todo':(context) => fireasa(),
        '/map':(context) =>loca()

      },
      home: test(),
    ),
  );
}

class test extends StatefulWidget {
  @override
  _testState createState() => _testState();
}

class _testState extends State<test> {

  List item=["First","Second","Third","Fourth","Fifth"];
  String text1 = "null";
  int _current_index = 0;
 // int _current_drawer_index = 0;
  //Color col=Colors.white;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: Drawer(
        child: ListView(
          children: [
            DrawerHeader(
              child: Center(
                  child: Text(
                "Drawer Test",
                style: TextStyle(
                    fontFamily: "Abel", fontSize: 50, color: Colors.white60),
              )),
              decoration: BoxDecoration(color: Colors.deepPurple[500]),
            ),
            // ListView.builder(itemBuilder: (BuildContext context, int index) {
            //   return ListTile(
            //
            //       selectedTileColor: Colors.deepPurple,
            //       // tileColor: col,
            //       onTap: (){
            //         setState(() {
            //           // col = Colors.deepPurple;
            //         });
            //         Toast.show("FIRST one", context);
            //       },
            //       title: Text(
            //         "item[index]",
            //         style: TextStyle(
            //           fontFamily: "Abel", fontSize: 30, ),
            //
            //     ),
            //   );
            // },),

            ListTile(
              selectedTileColor: Colors.deepPurple,
             // tileColor: col,
              onTap: (){
                setState(() {
                // col = Colors.deepPurple;
                });
                Toast.show("FIRST one", context);
              },
              title: Text(
                "First",
                style: TextStyle(
                    fontFamily: "Abel", fontSize: 30, ),
              ),
            ),
            ListTile(
             // tileColor: col,
              onTap: (){
                setState(() {
               //   col = Colors.deepPurple;
                });
                Toast.show("SECOND one", context);
              },
              title: Text(
                "Second",
                style: TextStyle(
                    fontFamily: "Abel", fontSize: 30, ),
              ),
            ),
            ListTile(
             // tileColor: col,
              onTap: (){
                setState(() {
                //  col = Colors.deepPurple;
                });
                Toast.show("THIRD one", context);
              },
              title: Text(
                "Third",
                style: TextStyle(
                    fontFamily: "Abel", fontSize: 30,),
              ),
            ),
            ListTile(
             // tileColor: col,
              onTap: (){
                setState(() {
               //   col = Colors.deepPurple;
                });
                Toast.show("FOURTH one", context);
              },
              title: Text(
                "Fourth",
                style: TextStyle(
                    fontFamily: "Abel", fontSize: 30, ),
              ),
            ),
            ListTile(
              //tileColor: col,
              onTap: (){
                setState(() {
               //   col = Colors.deepPurple;
                });
                Toast.show("FIFTH one", context);
              },
              title: Text(
                "Fifth",
                style: TextStyle(
                    fontFamily: "Abel", fontSize: 30, ),
              ),
            ),
          ],
        ),
       ),
      bottomNavigationBar: BottomNavigationBar(

        selectedItemColor: Colors.amberAccent,
        type: BottomNavigationBarType.fixed,
        backgroundColor: Colors.deepPurple,
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.list),
            label: "List View",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.article_outlined),
            label: "Words",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.list),
            label: "Mongo List",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.local_fire_department_sharp),
            label: "Firestore",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.add),
            label: "TODO",
          ),
        ],
        currentIndex: _current_index,
        onTap: tapped,
      ),
      appBar: AppBar(
        title: Text("TEST APP"),
        backgroundColor: Colors.deepPurple,
      ),
      body: Container(
        child: Center(
          child: Padding(
            padding: const EdgeInsets.all(10),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                FlatButton(
           // shape: Border.all(width: 2,style: BorderStyle.solid) , // gives border
shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(50)), //ROUNDED BORDER
                  splashColor: Colors.lightGreenAccent,
                  minWidth: 350,
                  color: Colors.deepPurple,
                  child: Text(
                    "Next Page ",
                    style: TextStyle(
                        fontSize: 50, fontFamily: "Abel", color: Colors.white),
                  ),
                  onPressed: () {
                    //  Navigator.pushNamed(context, '/list');
                    Navigator.push(
                        context, MaterialPageRoute(builder: (context) => words()));
                  },
                ),
                FlatButton(
                  // shape: Border.all(width: 2,style: BorderStyle.solid) , // gives border
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(50)), //ROUNDED BORDER
                  splashColor: Colors.lightGreenAccent,
                  minWidth: 350,
                  color: Colors.deepPurple,
                  child: Text(
                    "Map ",
                    style: TextStyle(
                        fontSize: 50, fontFamily: "Abel", color: Colors.white),
                  ),
                  onPressed: () {
                    //  Navigator.pushNamed(context, '/list');
                    Navigator.push(
                        context, MaterialPageRoute(builder: (context) => loca()));
                  },
                ),
                FlatButton(
                  // shape: Border.all(width: 2,style: BorderStyle.solid) , // gives border
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(50)), //ROUNDED BORDER
                  splashColor: Colors.lightGreenAccent,
                  minWidth: 350,
                  color: Colors.deepPurple,
                  child: Text(
                    "Notification ",
                    style: TextStyle(
                        fontSize: 50, fontFamily: "Abel", color: Colors.white),
                  ),
                  onPressed: () {
                    //  Navigator.pushNamed(context, '/list');
                    Navigator.push(
                        context, MaterialPageRoute(builder: (context) => noti()));
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void tapped(int index) {
    switch (index) {
      case 0:print("tapped");
        Navigator.pushNamed(context, '/list');
        break;
      case 1:print("tapped");
        Navigator.pushNamed(context, '/words');
        break;
      case 2:print("tapped");
              mongoclass().mongodata();
              CircularProgressIndicator();
              Future.delayed(Duration(seconds: 5),(){Navigator.pushNamed(context, "/mongolist");});
              break;
      case 3:print("tapped");
      Navigator.pushNamed(context, '/firebase');
      break;
      case 4:print("tapped");
      Navigator.pushNamed(context, '/todo');
      break;

      default:
        break;
    }
    setState(() {
      _current_index = index;
    });
  }
}
