import 'package:flutter/material.dart';
import 'package:list_english_words/list_english_words.dart';
import 'package:toast/toast.dart';
int inde;
void main() {

  runApp(listview());
}
class listview extends StatefulWidget {
  @override
  _listviewState createState() => _listviewState();
}

class _listviewState extends State<listview> {

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("ListView"),
          backgroundColor: Colors.deepPurple,
        ),
        body: Container(
          child: ListView.builder(
            //itemCount: wordList.length,
            itemCount: list_english_words.length,
              itemBuilder: (BuildContext context, int index) {

                return ListTile(
                  hoverColor: Colors.deepPurple,
                 //tileColor: Colors.lightGreenAccent,
                  onTap: () {
                    showDialog(
                      context: context,
                      builder: (BuildContext context) => _pop_up(context),
                    );
                    setState(() {
                      inde=index;
                    });
                    _pop_up(context);
                    // final c=OTP.generateTOTPCode("JBSWY3DPEHPK3PXP", DateTime.now().millisecond,length: 6,interval: 1);
                    // print(c);
                    Toast.show(
                      list_english_words[index],
                      context,
                      duration: 3,
                      gravity: Toast.BOTTOM,
                      backgroundRadius: 100,
                      backgroundColor: Colors.black12,
                      textColor: Colors.black,
                    );
                  },

                  title: Text(list_english_words[index]),
                  trailing: Icon(Icons.whatshot,color: Colors.amber,),
                );
              }),
        ) ,
      ),
    );



  }
}
Widget _pop_up(BuildContext context){
  return AlertDialog(
    title: const Text("Selected word"),
    content: Text(list_english_words[inde]),
    actions: [
      FlatButton(child: Text("OK"),onPressed: Navigator.of(context).pop,)
    ],
  );

}
// Widget _buildAboutDialog(BuildContext context) {
//   return new AlertDialog(
//       title: const Text('About Pop up'),
//       content: new Column(
//         mainAxisSize: MainAxisSize.min,
//         crossAxisAlignment: CrossAxisAlignment.start,
//         children: <Widget>[
//          Text("TEST>>"),
//           Text(">>>>>"),
//         ],
//       ),
//       actions: <Widget>[
//   new FlatButton(
//   onPressed: () {
//     Navigator.of(context).pop();
//   },
//   textColor: Theme.of(context).primaryColor,
//   child: const Text('Okay, got it!'),
//   ),
//   ],
//   );
// }

