
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';

void main(){
  runApp(noti());
}
class noti extends StatefulWidget {
  @override
  _notiState createState() => _notiState();
}
void notifica(){
  print("IN NOTIFICATION");
FlutterLocalNotificationsPlugin flip=new FlutterLocalNotificationsPlugin();
// var android = new AndroidInitializationSettings('@mipmap/ic_launcher');
// var IOS = new IOSInitializationSettings();
var settings = new InitializationSettings(android: new AndroidInitializationSettings('@mipmap/ic_launcher'),iOS: new IOSInitializationSettings());
flip.initialize(settings);
show_notification(flip);
  print("EXITED NOTIFICATION");
 Future.value(true);

}

Future show_notification(flip) async {
  print("ENTERED SHOW NOTIFICATION");
  var androidplatform= new AndroidNotificationDetails("channelId", "1", "something");
  var iosplatform  = new IOSNotificationDetails();
  var platform = new NotificationDetails(android: androidplatform,iOS: iosplatform);
  await flip.show(0, 'NOTIFICATION TEST',
      'OK TESTED',
      platform, payload: 'Default_Sound'
  );
  print("EXITED SHOW NOTIFICATION");
}
class _notiState extends State<noti> {

@override
  Widget build(BuildContext context) {


    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(backgroundColor: Colors.green,title: Text("noticication"),),
        body: Center(
          child: FlatButton(
            color: Colors.green,
            child: Text("Notification"),
            onPressed: (){
notifica();
              print("clicked");
            },
          ),
        ),
      ),
    );
  }
}
