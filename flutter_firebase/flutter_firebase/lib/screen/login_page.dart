import 'package:flutter/material.dart';
void main(){
  runApp(login());
}

class login extends StatefulWidget {
  @override
  _loginState createState() => _loginState();
}

class _loginState extends State<login> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.red,
          title:Text("Login Page"),
        ),
      ),
    );
  }
}


