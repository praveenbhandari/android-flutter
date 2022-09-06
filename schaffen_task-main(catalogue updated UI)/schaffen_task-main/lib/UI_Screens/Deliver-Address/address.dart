import 'package:flutter/material.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/UI_Screens/Deliver-Address/Components/body.dart';
class DeliAddress extends StatelessWidget {
  const DeliAddress({Key? key}) : super(key: key);
  static String routeName = "/address";
  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    return Scaffold(
      appBar: AppBar(),
      body: Center(
        child: SizedBox(

          child: const Body(),
        ),
      ),
    );
  }
}
