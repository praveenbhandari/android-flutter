import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

void main() {
  print("In todo");
  runApp(fireasa());
}

CollectionReference users = FirebaseFirestore.instance.collection('user');
User auth=FirebaseAuth.instance.currentUser;
class fireasa extends StatefulWidget {
  @override
  _fireasaState createState() => _fireasaState();
}

class _fireasaState extends State<fireasa> {


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
          return todo();
        }

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


class todo extends StatefulWidget {
  @override
  _todoState createState() => _todoState();
}
List data = [];
//FirebaseFirestore fire=FirebaseFirestore.instance;

class _todoState extends State<todo> {
  TextEditingController itemtoadd = TextEditingController();
void initState(){
  super.initState();

    users.doc(auth.uid).get().then((value) {
      var d=value.data();
      //print(value["1"]);
      for(int i=0;i<d.length;i++){
        String valu=value[i.toString()];
        setState(() {
          data.add(valu);
          print(valu);
        });
      }
      // data.add(d.toString());
      print(data);

    });



}
  _displayDialog(BuildContext context) async {
    return showDialog(
        context: context,
        builder: (context) {
          return Container(

            decoration: BoxDecoration(color: Colors.black12,borderRadius: BorderRadius.circular(10)),
            child: AlertDialog(
              title: Text('Add This To List',style:TextStyle(),),
              content: TextField(
                controller: itemtoadd,
                textInputAction: TextInputAction.go,
                decoration: InputDecoration(hintText: "Item"),
              ),
              actions: <Widget>[
                new FlatButton(
                  child: new Text('Add'),
                  onPressed: () {
                    setState(() {
                     int inde=data.length;
                      firesend(inde.toString(),itemtoadd.text);

                     data.add(itemtoadd.text);
                      Navigator.of(context).pop();
                    });

                  },
                )
              ],
            ),
          );
        });
  }
  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.grey,
        floatingActionButton: FloatingActionButton(
          onPressed: () {


            setState(() {
              _displayDialog(context);
            //  oncreate();
            });


          },
          child: Icon(
            Icons.add,
            color: Colors.amber,
          ),
          backgroundColor: Colors.black,
        ),
        body: SafeArea(
          child: dat(),
        ),
      ),
    );
  }

}




class dat extends StatefulWidget {
  @override
  _datState createState() => _datState();
}

class _datState extends State<dat> {

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: data.length,
      itemBuilder: (BuildContext context, int index) {
        return ListTile(
          title: Card(
            shadowColor: Colors.blueGrey,
            color: Colors.lightGreenAccent,
            child: Padding(
              padding: const EdgeInsets.all(10.0),
              child: Row(

                mainAxisAlignment: MainAxisAlignment.start,
                mainAxisSize: MainAxisSize.max,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(left: 10),
                    child:new Container(
                      child:new Text(
                        data[index],overflow: TextOverflow.ellipsis,
                        maxLines: 1,
                        softWrap: false,
                        // maxLines: 3,
                        // softWrap: true,
                        style: TextStyle(
                            fontFamily: 'Abel',
                            fontSize: 30,
                            fontWeight: FontWeight.bold),
                      ),
                    ),
                  ),
                  new Spacer(),
                  IconButton(
                    onPressed: (){
                      setState(() {
                        users.doc(auth.uid).update({index.toString(): FieldValue.delete()}).then((value) => print("removed from db"));
                       print(data);
                        data.removeAt(index);

                      });
                      for(int i=0;i<data.length;i++){
                        print(data.length);
                        users.doc(auth.uid).update({
                          i.toString():data[i]
                        });}
                      int updatedlen=data.length;
                      users.doc(auth.uid).update({updatedlen.toString(): FieldValue.delete()}).then((value) => print("removed from db"));
                     // print(data);
                    },
                    icon:Icon(Icons.close),
                    color: Colors.black,
                  ),
                ],
              ),
            ),
          ),
          // trailing: Container(
          //   child: Icon(
          //     Icons.close,
          //     color: Colors.black,
          //   ),
          //   color: Colors.lightGreenAccent,
          // ),
        );
      },
    );
  }

}

void firesend(String data,String value) {

  print(auth.uid);
  users.doc(auth.uid).update({data:value}).then((value) => print('uploaded'));
  // users.doc(auth.uid).get().then((value) {
  //   print(value['']);
  //
  // });
}












