import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app_test/mongodtest.dart';

void main() {
  runApp(mongolist());
}

class mongolist extends StatefulWidget {
  @override
  _mongolistState createState() => _mongolistState();
}

class _mongolistState extends State<mongolist> {
  List name = mongoclass.name;
  List address = mongoclass.address;
  List phone = mongoclass.phone;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: ListView.builder(
          itemCount: name.length,
          itemBuilder: (BuildContext context, int index) {
            return
                //   Card(child:Text(name[index],style: TextStyle(fontSize: 50),) , );
                Column(
              children: [
                ListTile(
                  tileColor: Colors.white70,
                  leading: Icon(
                    Icons.person,
                    color: Colors.deepPurple,
                  ),
                  title: Text(name[index]),
                  trailing: Text(phone[index]),
                  subtitle: Text(address[index]),
                ),
                Divider(
                  thickness: 1,
                  color: Colors.grey,
                )
              ],
            );
          },
        ),
      ),
    );
  }
}
