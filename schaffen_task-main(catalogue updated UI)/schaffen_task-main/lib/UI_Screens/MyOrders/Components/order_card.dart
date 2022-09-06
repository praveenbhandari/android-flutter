import 'package:flutter/material.dart';
import 'package:schaffen_task/Components/custom_text.dart';
import 'package:schaffen_task/Constants/size_config.dart';

class OrderCard extends StatelessWidget {
  OrderCard({
    Key? key,
    this.restaurant,
    this.date,
    this.isDelivered,
    this.orderId,
  }) : super(key: key);
  String? restaurant;
  String? date;
  String? orderId;
  bool? isDelivered;
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        child: Container(
          margin: const EdgeInsets.all(15),
          height: getProportionateScreenHeight(100),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              CustomText(
                text: restaurant!,
                fontSize: 20,
                fontWeight: FontWeight.w700,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  RichText(
                    text: TextSpan(
                      text: 'OrderId: ',
                      style: const TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.w500,
                        fontSize: 16,
                      ),
                      children: <TextSpan>[
                        TextSpan(
                            text: orderId!,
                            style: const TextStyle(
                              fontWeight: FontWeight.w300,
                              fontSize: 14,
                            )),
                      ],
                    ),
                  ),
                  RichText(
                    text: TextSpan(
                      text: 'Date: ',
                      style: const TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.w500,
                        fontSize: 16,
                      ),
                      children: <TextSpan>[
                        TextSpan(
                          text: date!,
                          style: const TextStyle(
                            fontWeight: FontWeight.w300,
                            fontSize: 14,
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
              RichText(
                text: TextSpan(
                  text: 'Status: ',
                  style: const TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.w500,
                    fontSize: 18,
                  ),
                  children: <TextSpan>[
                    TextSpan(
                      text: isDelivered! ? 'Delivered' : 'Processing',
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 16,
                        color: isDelivered! ? Colors.green : Colors.red,
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
