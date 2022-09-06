import 'package:flutter/material.dart';
import 'package:schaffen_task/Components/custom_text.dart';
import 'package:schaffen_task/Constants/constants.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/UI_Screens/Deliver-Address/Components/address_card.dart';
import 'package:schaffen_task/UI_Screens/Deliver-Address/address.dart';

class PreviousAddress extends StatelessWidget {
  const PreviousAddress({Key? key}) : super(key: key);
  static String routeName = "/previous";
  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        backgroundColor: kPrimaryColor,
        title: CustomText(
          text: 'My Addresses',
          fontWeight: FontWeight.bold,
          fontSize: 22,
        ),
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(15),
            bottomRight: Radius.circular(15),
          ),
        ),
      ),
      bottomNavigationBar: BottomAppBar(
        child: InkWell(
          onTap: ()
          {
            Navigator.pushNamed(context, DeliAddress.routeName);
          },
          child: Container(
            height: getProportionateScreenHeight(50),
            decoration: const BoxDecoration(
              borderRadius: BorderRadius.only(
                topLeft: Radius.circular(20),
                topRight: Radius.circular(20),
              ),
              color: Colors.greenAccent,
            ),
            child: Center(
              child: CustomText(
                  text: 'Add New Address',
                  fontSize: 24,
                  fontWeight: FontWeight.bold,

              ),
            ),
          ),
        ),
      ),

        body: ListView.builder(
          scrollDirection: Axis.vertical,
          shrinkWrap: true,
            itemCount: 2,
            itemBuilder: (context, index) {
              return AddressCard(
                address: 'Thakur Kala Apt, Vikas Nagar',
                phoneNo: '7905709124',
                firstName: 'Sanskar',
                lastName: 'Modi',
                city:'Lucknow',
              );
            }));
  }
}
