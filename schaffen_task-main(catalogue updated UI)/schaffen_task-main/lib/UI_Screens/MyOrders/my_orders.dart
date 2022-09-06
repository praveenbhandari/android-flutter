import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:schaffen_task/Components/custom_text.dart';
import 'package:schaffen_task/Constants/constants.dart';

import 'package:schaffen_task/Constants/size_config.dart';

import 'Components/body.dart';

class MyOrders extends StatelessWidget {
  const MyOrders({Key? key}) : super(key: key);
  static String routeName = "/my_order";
  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        backgroundColor: kPrimaryColor,
        title: CustomText(
          text: 'My Orders',
          fontSize: 22,
          fontWeight: FontWeight.bold,
        ),
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(15),
            bottomRight: Radius.circular(15),
          ),
        ),
      ),
      body: Center(
        child: SizedBox(
          child: Body(),
        ),
      ),
    );
  }
}
