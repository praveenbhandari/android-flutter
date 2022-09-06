import 'package:flutter/material.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/Constants/ui.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/veg_portion.dart';

class FoodCard extends StatefulWidget {
   FoodCard({Key? key,this.isVeg,this.price,this.food}) : super(key: key);
double? price;
String? food;
bool? isVeg;
  @override
  State<FoodCard> createState() => _FoodCardState();
}

class _FoodCardState extends State<FoodCard> {
  int order=0;
  @override
  Widget build(BuildContext context) {

    return  Row(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children: <Widget>[
        VegBadgeView(isVeg: widget.isVeg!,),
        UIHelper.horizontalSpaceSmall(),
        Container(
          width: getProportionateScreenWidth(150),
          child: FittedBox(
            fit: BoxFit.cover,
            child: Text(
              widget.food!,
              style: Theme.of(context).textTheme.bodyText1,
            ),
          ),
        ),
        UIHelper.horizontalSpaceSmall(),
        Container(
          padding: const EdgeInsets.symmetric(horizontal: 5.0),
          height: 35.0,
          width: 100.0,
          decoration: BoxDecoration(
            border: Border.all(
              color: Colors.grey,
            ),
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              InkWell(
                child: const Icon(Icons.remove, color: Colors.green),
                onTap: () {
                  order>0?decrement(): null;
                },
              ),
              const Spacer(),
              Text('$order',
                  style: Theme.of(context)
                      .textTheme
                      .subtitle2!
                      .copyWith(fontSize: 16.0)),
              const Spacer(),
              InkWell(
                child: const Icon(Icons.add, color: Colors.green),
                onTap: () {
                  increment();
                },
              )
            ],
          ),
        ),
        UIHelper.horizontalSpaceSmall(),
        Text(
          '${order*widget.price!}',
          style: Theme.of(context).textTheme.bodyText1,
        ),
      ],
    );
  }

  void increment({int? price})
  {
    setState(() {
      order++;

    });

  }

  void decrement({int? price})
  {
    setState(() {
      order--;

    });

  }
}
