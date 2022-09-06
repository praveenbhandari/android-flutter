import 'dart:math';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
void main() {
  return runApp(DicePage()
//    MaterialApp(
//      home: Scaffold(
//        backgroundColor: Colors.red,
//        appBar: AppBar(
//          title: Text('Dicee'),
//          backgroundColor: Colors.red,
//        ),
//        body: DicePage(),
//      ),
//    ),
  );
}

                                                        ///////use only if the state of our app changes if not then stateless widget
class DicePage extends StatefulWidget {
  @override
  _DicePageState createState() => _DicePageState();
}

class _DicePageState extends State<DicePage> {
  int left =1 ;
  int right =1 ;

  void as(){
    setState(() {
      right=Random().nextInt(6) + 1;
      print(right);
      left=Random().nextInt(6) + 1;
      print(left);
    });
  }
  void a(){
    if (right == left)
      {
        Fluttertoast.showToast(
            msg: "WOW, CONGRULATIONS YOU GOT IT",
            toastLength: Toast.LENGTH_SHORT,
            gravity: ToastGravity.BOTTOM,
            timeInSecForIosWeb: 1,
            backgroundColor: Colors.white,
            textColor: Colors.black,
            fontSize: 16.0
        );
      }
  }
  @override
  Widget build(BuildContext context) {


    return  MaterialApp(
        home: Scaffold(
        backgroundColor: Colors.red,
        appBar: AppBar(
        title: Text('Dicee'),
    backgroundColor: Colors.red,
    ),
    body: Center(
      child: Row(
          children: <Widget>[
            Expanded(
              ////////used for image to take up asmuch space it can
              //  flex: 2,//twice the width of 2nd '1'
              child: Padding(
                padding: const EdgeInsets.all(15),
                child: FlatButton(
                  onPressed: (){ as();a();},
                  child: Image.asset('images/dice$left.png'),
                ),
              ),
            ),
            Expanded(
              //flex:1,//smaller than upper image
              child: Padding(
                padding: const EdgeInsets.all(15),
                child: FlatButton(
                  onPressed: (){as();a(); },
                  child: Image.asset('images/dice$right.png'),
                ),
              ),
            ),
          ],
        ),
    ),
    ),
    );
  }
}
                      /////no need if we have to change the images  or contents of the app ( use statefull if the content changes in app)
//
//class DicePage extends StatelessWidget {
//
//}
