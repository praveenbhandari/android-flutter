

import 'package:flutter/material.dart';

class AddBtnView extends StatelessWidget {
  bool? isAdd=false;
  AddBtnView({
    Key? key,this.isAdd
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 6.0, horizontal: 25.0),
      decoration: BoxDecoration(
        border: Border.all(color: Colors.grey),
      ),
      child: Text(
        isAdd==true?'ADDED':'ADD',
        style: Theme.of(context)
            .textTheme
            .subtitle2!
            .copyWith(color: Colors.green),
      ),
    );
  }
}