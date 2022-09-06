import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
class p_card_w extends StatelessWidget {
  p_card_w({Key? key,required this.namee,required this.icons,required this.route}) : super(key: key);
  String namee,route;
  IconData icons;
  @override
  Widget build(BuildContext context) {
    return Container(
        width: 500,
        height: 70,
        decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(10),
            color: Colors.black),
        child: Card(
          color: Colors.black,
          child: Center(
            child: Text(
              namee,
              style: TextStyle(color: Colors.amber,fontSize: 20,fontWeight: FontWeight.bold),
            ),
          ),
        ))
    ;
  }
}