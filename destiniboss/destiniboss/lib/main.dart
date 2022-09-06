import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.orange,
        body: txt(),
      ),
    );
  }
}

class txt extends StatefulWidget {
  @override
  _txt createState() => _txt();
}

class _txt extends State<txt> {
  @override
  Widget build(BuildContext context) {
    return  Container(

       // padding: EdgeInsets.symmetric(vertical: 50.0, horizontal: 15.0),
        child: SafeArea(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.stretch,
          //  mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Expanded(
                flex:12,
                child: Center(
                  child: Text("options",
                    style: TextStyle(fontSize: 50,),),
                ),
              ),
      SizedBox(
      height: 10,
    ),
              Expanded(
                flex:2,
                child: Padding(
                  padding: const EdgeInsets.all(5.0),
                  child: FlatButton(

                    color: Colors.redAccent,
                    child: Text(
                      "OPTION 1",
                      style: TextStyle(fontSize: 30),
                    ),
                    onPressed: (){},
                  ),
                ),
              ),
//              SizedBox(
//                height: 10,
//              ),
              Expanded  (
                flex:2,
                child: Padding(
                  padding: const EdgeInsets.all(5.0),
                  child: FlatButton(
                    color: Colors.indigoAccent,
                    child: Text(
                      "OPTION 2",
                      style: TextStyle(fontSize: 30),
                    ),
                    onPressed:(){},
                  ),
                ),
              ),
            ],
          ),
        ),

    );
  }
}
