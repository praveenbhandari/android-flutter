import 'package:flutter/material.dart';
import 'package:schaffen_task/Constants/constants.dart';
import 'package:schaffen_task/Constants/ui.dart';
import 'package:schaffen_task/UI_Screens/Deliver-Address/address.dart';
import 'package:schaffen_task/UI_Screens/Order_Tracker/order_track.dart';

class AddressPaymentView extends StatelessWidget {
  double? total;
  String? address;

  AddressPaymentView({Key? key,this.total,this.address}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Container(
          height: 50.0,
          color: Colors.black,
          child: Row(
            children: <Widget>[
              Icon(Icons.phone, color: Colors.yellow[800]),
              UIHelper.horizontalSpaceSmall(),
              Expanded(
                child: Text(
                  'Want your order left outside? Call delivery executive',
                  style: Theme.of(context)
                      .textTheme
                      .bodyText1!
                      .copyWith(color: Colors.white),
                ),
              )
            ],
          ),
        ),
        Container(
          padding: const EdgeInsets.all(20.0),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Stack(
                children: <Widget>[
                  Container(
                    alignment: Alignment.center,
                    height: 60.0,
                    width: 60.0,
                    decoration: BoxDecoration(
                      border: Border.all(
                        color: Colors.grey,
                        width: 1.0,
                      ),
                    ),
                    child: const Icon(Icons.add_location, size: 30.0),
                  ),
                  const Positioned(
                    top: 0.0,
                    right: 0.0,
                    child: Icon(
                      Icons.check_circle,
                      color: Colors.green,
                    ),
                  )
                ],
              ),
              UIHelper.horizontalSpaceMedium(),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      'Deliver to Other',
                      style: Theme.of(context)
                          .textTheme
                          .subtitle2!
                          .copyWith(fontSize: 16.0),
                    ),
                    Text(
                      address!,
                      style: Theme.of(context)
                          .textTheme
                          .bodyText1!
                          .copyWith(color: Colors.grey),
                    ),
                    UIHelper.verticalSpaceSmall(),
                    Text(
                      '43 MINS',
                      style: Theme.of(context).textTheme.subtitle2,
                    ),
                  ],
                ),
              ),
              InkWell(

                child: Text(
                  'ADD ADDRESS',
                  style: Theme.of(context)
                      .textTheme
                      .subtitle2!
                      .copyWith(color: kPrimaryColor),
                ),
                onTap: () {
                  Navigator.pushNamed(context, DeliAddress.routeName);
                },
              ),
              UIHelper.verticalSpaceMedium(),
            ],
          ),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
              child: Container(
                padding: const EdgeInsets.all(10.0),
                color: Colors.grey[200],
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      '${total}',
                      style: Theme.of(context)
                          .textTheme
                          .subtitle2!
                          .copyWith(fontSize: 16.0),
                    ),
                    UIHelper.verticalSpaceExtraSmall(),
                    Text(
                      'VIEW DETAIL BILL',
                      style: Theme.of(context)
                          .textTheme
                          .subtitle2!
                          .copyWith(color: Colors.blue, fontSize: 13.0),
                    ),
                  ],
                ),
              ),
            ),
            Expanded(
              child: InkWell(
                onTap: (){
                  Navigator.pushNamed(context, OrderTracker.routeName);
                },
                child: Container(
                  alignment: Alignment.center,
                  padding: const EdgeInsets.all(10.0),
                  color: Colors.green,
                  height: 58.0,
                  child: Text(
                    'PROCEED TO PAY',
                    style: Theme.of(context)
                        .textTheme
                        .subtitle2!
                        .copyWith(color: Colors.white),
                  ),
                ),
              ),
            )
          ],
        )
      ],
    );
  }
}