
import 'package:flutter/material.dart';

import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/Models/cart_models.dart';

import 'cart_card.dart';

class Body extends StatefulWidget {
  @override
  _BodyState createState() => _BodyState();
}

class _BodyState extends State<Body> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(
        horizontal: getProportionateScreenWidth(20)!,
      ),
      child: ListView.builder(
        itemCount: demoCarts!.length,
        itemBuilder: (context, index) =>  Padding(
          padding: EdgeInsets.symmetric(vertical: 10),
          child:CartCard(cart: demoCarts![index],),
          // Dismissible(
          //   key: Key(demoCarts[index].product.id.toString()),
          //   direction: DismissDirection.endToStart,
          //   onDismissed: (direction) {
          //     setState(() {
          //       demoCarts.removeAt(index);
          //     });
          //   },
          //   background: Container(
          //     padding: const EdgeInsets.symmetric(horizontal: 20),
          //     decoration: BoxDecoration(
          //       color: const Color(0xFFFFE6E6),
          //       borderRadius: BorderRadius.circular(15),
          //     ),
          //     child: Row(
          //       children: [
          //         const Spacer(),
          //         SvgPicture.asset("assets/icons/Trash.svg"),
          //       ],
          //     ),
          //   ),
          //   child: CartCard(cart: demoCarts[index]),
          // ),
        ),
      ),
    );
  }
}
