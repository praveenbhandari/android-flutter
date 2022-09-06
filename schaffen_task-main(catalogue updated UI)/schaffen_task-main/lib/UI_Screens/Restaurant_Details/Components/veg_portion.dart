import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:schaffen_task/Provider/provider.dart';

class VegBadgeView extends StatelessWidget {
   VegBadgeView({Key? key,this.isVeg}) : super(key: key);
   bool? isVeg=true;
  @override
  Widget build(BuildContext context) {
    final _counter = Provider.of<CounterModel>(context);
    return Container(
      padding: const EdgeInsets.all(2.0),
      height: 15.0,
      width: 15.0,
      decoration: BoxDecoration(
        border: Border.all(
          color: _counter.isVeg&&isVeg==true ? Colors.green[800]! : Colors.redAccent,
        ),
      ),
      child: ClipOval(
        child: Container(
          height: 5.0,
          width: 5.0,
          color: _counter.isVeg&&isVeg==true ? Colors.green[800]! : Colors.redAccent,
        ),
      ),
    );
  }
}
