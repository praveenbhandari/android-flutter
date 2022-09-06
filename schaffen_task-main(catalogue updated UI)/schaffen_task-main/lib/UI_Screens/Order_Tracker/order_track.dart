import 'package:flutter/material.dart';
import 'package:schaffen_task/UI_Screens/Order_Tracker/Components/body.dart';

class OrderTracker extends StatelessWidget {
  const OrderTracker({Key? key}) : super(key: key);
  static String routeName = "/order_tracker";
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Body(),
    );
  }
}
