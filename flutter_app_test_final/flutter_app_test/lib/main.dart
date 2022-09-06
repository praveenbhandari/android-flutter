import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app_test/firebase_test.dart';
import 'package:flutter_app_test/mongodtest.dart';
import 'package:flutter_app_test/mongolist.dart';
import 'package:flutter_app_test/words.dart';
import 'package:toast/toast.dart';
import 'firebase_authentication.dart';
import 'listview.dart';


void main()  {

  runApp(
    MaterialApp(
      initialRoute: '/',
      routes: {
        '/list': (context) => listview(),
        '/words': (context) => words(),
        '/firebase': (context) => fire(),
        '/mongolist':(context) =>mongolist(),
        '/login':(context) => login()

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
            child: FlatButton(
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
              mongoclass().mongodata().whenComplete(() => Navigator.pushNamed(context, "/mongolist"));
              break;
      case 3:print("tapped");
      Navigator.pushNamed(context, '/firebase');
      break;

      default:
        break;
    }
    setState(() {
      _current_index = index;
    });
  }
}
