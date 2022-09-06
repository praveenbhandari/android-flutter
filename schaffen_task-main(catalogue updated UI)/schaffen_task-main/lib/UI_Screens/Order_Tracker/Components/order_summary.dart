import 'package:flutter/material.dart';

import 'package:schaffen_task/Constants/ui.dart';

class BillDetailView extends StatelessWidget {
  double? deliverFee;
  double? total;
  double?taxes;
   BillDetailView({Key? key,this.total,this.deliverFee,this.taxes}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    final textStyle =
    Theme
        .of(context)
        .textTheme
        .bodyText1!
        .copyWith(fontSize: 16.0);

    return Container(
      padding: const EdgeInsets.all(20.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            'Bill Details',
            style:
            Theme
                .of(context)
                .textTheme
                .headline6!
                .copyWith(fontSize: 17.0),
          ),
          UIHelper.verticalSpaceSmall(),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Text('Item total', style: textStyle),
              Text('${total!}', style: textStyle),
            ],
          ),
          UIHelper.verticalSpaceMedium(),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Flexible(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisSize: MainAxisSize.min,
                  children: <Widget>[
                    Row(
                      children: <Widget>[
                        Text('Delivery Fee', style: textStyle),
                        UIHelper.horizontalSpaceSmall(),
                        const Icon(Icons.info_outline, size: 14.0)
                      ],
                    ),
                    UIHelper.verticalSpaceSmall(),
                    Text(
                      'Your Delivery Partner is travelling long distance to deliver your order',
                      style: Theme
                          .of(context)
                          .textTheme
                          .bodyText1!
                          .copyWith(fontSize: 13.0),
                    ),
                  ],
                ),
              ),
              Text('${deliverFee!}', style: textStyle),
            ],
          ),
          UIHelper.verticalSpaceLarge(),
          buildDivider(),
          Container(
            alignment: Alignment.center,
            height: 60.0,
            child: Row(
              children: <Widget>[
                Text('Taxes and Charges', style: textStyle),
                UIHelper.horizontalSpaceSmall(),
                const Icon(Icons.info_outline, size: 14.0),
                const Spacer(),
                Text('${taxes!}', style: textStyle),
              ],
            ),
          ),
          buildDivider(),
          Container(
            alignment: Alignment.center,
            height: 60.0,
            child: Row(
              children: <Widget>[
                Text('To Pay', style: Theme
                    .of(context)
                    .textTheme
                    .subtitle2),
                const Spacer(),
                Text('${deliverFee!+total!+taxes!}', style: textStyle),
              ],
            ),
          ),
        ],
      ),
    );
  }}