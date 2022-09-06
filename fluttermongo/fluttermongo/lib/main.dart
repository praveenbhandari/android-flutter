import 'package:flutter/material.dart';
import 'package:mongo_dart/mongo_dart.dart';


Future<void> main() async {
 var authors = <String, Map>{};
 final db=await Db.create("mongodb+srv://praveen:praveen@fluttertest.y9rm6.mongodb.net/flutter_test?retryWrites=true&w=majority");
 await db.open().whenComplete(() => print("DONE"));
 var col=db.collection("test");
 // await col.insertAll([
 //  {
 //   'name': 'William Shakespeare',
 //   'email': 'william@shakespeare.com',
 //   'age': 587
 //  },
 //  {'name': 'Jorge Luis Borges', 'email': 'jorge@borges.com', 'age': 123}
 // ]);
 // await col.find().forEach((v) {
 //  //print(v);
 //  authors[v['name'].toString()] = v;
 //
 //
 // });
 // print(authors);
await db.collection('test').find().forEach((element) {
 print(element['name']);
}
 );
//print(a);
 db.close();

}
