import 'package:flutter/material.dart';
import 'package:schaffen_task/Components/custom_text.dart';
import 'package:schaffen_task/Constants/constants.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/UI_Screens/Cart/cart.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/body.dart';

class RestaurantDetails extends StatelessWidget {
  const RestaurantDetails({Key? key}) : super(key: key);
  static String routeName = "/restaurant_detail";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: BottomAppBar(

        child: InkWell(
          onTap: ()
          {
            Navigator.pushNamed(context, CartScreen.routeName);
          },
          child: Container(
            height: getProportionateScreenHeight(50),
            decoration: const BoxDecoration(
              borderRadius: BorderRadius.only(
                topLeft: Radius.circular(20),
                topRight: Radius.circular(20),
              ),
              color: kPrimaryColor,
            ),
            child: Center(
              child: CustomText(
                  text: 'Check Cart',
                  fontSize: 24,
                  fontWeight: FontWeight.w700
              ),
            ),
          ),
        ),
      ),
      body: Body(),
    );
  }
}
