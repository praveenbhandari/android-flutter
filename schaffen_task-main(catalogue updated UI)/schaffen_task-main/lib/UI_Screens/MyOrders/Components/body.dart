import 'package:flutter/material.dart';

import 'package:schaffen_task/UI_Screens/MyOrders/Components/order_card.dart';

class Body extends StatelessWidget {
  const Body({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
        scrollDirection: Axis.vertical,
        shrinkWrap: true,
          itemCount: 3,
          itemBuilder: (context, index) {
            return Padding(
              padding: const EdgeInsets.all(8.0),
              child: OrderCard(
                date: '29-01-2022',
                isDelivered: true,
                orderId: '#34523',
                restaurant: 'Mahalaxmi',
              ),
            );
          }),
    );
  }
}
