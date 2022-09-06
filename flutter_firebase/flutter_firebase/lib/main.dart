import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_firebase/screen/home_page.dart';
import 'screen/login_page.dart';

void main() {
  runApp(maint());
}

class maint extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      //  initialRoute: "home",
      routes: {
        '/main': (context) => ma(),
        "/login": (context) => login(),
        "home": (context) => home()
      },
      home: ma(),
    );
  }
}

class ma extends StatefulWidget {
  @override
  _maState createState() => _maState();
}

class _maState extends State<ma> {
  final GlobalKey<FormState> _formkey = GlobalKey<FormState>();
  String _email, _pass;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Form(
          key: _formkey,
          child: Column(
            children: <Widget>[
              TextFormField(
                decoration: InputDecoration(labelText: "Email"),
                validator: (input) {
                  if (input == null) {
                    return "ENTER YOUR EMAIL";
                  }
                },
                onSaved: (input) => _email = input,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: "Password"),
                onSaved: (input) => _pass = input,
                obscureText: true,
              ),
              FlatButton(
                child: Text("SignIn"),
                onPressed: () {
                  signin();
                },
              ),
            ],
          ),
        ),
//        body: FlatButton(
//          child: Text("data"),
//          onPressed: (){
//            Navigator.pushNamed(context, '/login');
//          },
//        ),
      ),
    );
  }

  Future signin() async {
    if(_formkey.currentState.validate()){
      _formkey.currentState.save();
      try{
        await FirebaseAuth.instance.signInAnonymously();
//     await FirebaseAuth.instance.createUserWithEmailAndPassword(email: _email, password: _pass); 
       Navigator.pushNamed(context, "/login");
      }
      catch(e){
        print("ERROR"+e.toString());
      }
    }
  }
}
