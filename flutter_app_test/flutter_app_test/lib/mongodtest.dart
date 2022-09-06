
import 'package:flutter_app_test/firebase_test.dart';
import 'package:mongo_dart/mongo_dart.dart';

 class mongoclass extends firebase_test{
  static List d = new List();
  static List name=new List();
  static List phone=new List();
  static List address=new List();
  static List pin=new List();
  Future<void> mongodata() async {


    var upload=firebase_test.a;
   // print("got ${upload}");

    var db = await Db.create( "mongodb+srv://praveen:praveen@fluttertest.y9rm6.mongodb.net/flutter_test?retryWrites=true&w=majority");

    await db.open();
    print("done");

    var collection=db.collection("test");
   // await collection.insert({"somedata":"${upload}"});
    //await db.collection("test").insert({"something":"something"});
    await collection.find().forEach((element) {
      d.add(element['name']);
      name.add(element['name']);
      phone.add(element['phone']);
      address.add(element['address']);
      pin.add(element['pin']);
     // print(element["address"]);
    });

    // for (int i = 0; i < d.length; i++) {
    //   //print(d[i]);
    //   print(name[i]);
    //   print(phone[i]);
    //   print(address[i]);
    //   print(pin[i]);
    // }
    db.close();
  }
}


//from firebase_test


// void back() async{
//   mongoclass m=new mongoclass();
//
//   await m.mongodata().whenComplete(() => print("done getting"));
//   List data=mongoclass.d;
//
//   print(data);
//   setState(() {
//     data1=data[0];
//     data2=data[1];
//     Navigator.push(context, MaterialPageRoute(builder: (builder) => listview()));
//   });
//
// }

