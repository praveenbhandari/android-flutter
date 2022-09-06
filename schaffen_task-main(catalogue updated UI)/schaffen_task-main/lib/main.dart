import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:schaffen_task/Provider/provider.dart';
import 'package:schaffen_task/Routes/routes.dart';
import 'package:schaffen_task/Themes/themes.dart';
import 'package:firebase_core/firebase_core.dart';
import 'UI_Screens/log_in.dart';

void main()async {
    WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider.value(
      value:CounterModel(),
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Schaffen',
        theme: theme(),
        initialRoute: LogIn.routeName,
        routes: routes,
      ),
    );
  }
}
