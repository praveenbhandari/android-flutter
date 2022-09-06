import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:schaffen_task/Components/custom_text.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/Models/cart_models.dart';
import 'package:schaffen_task/Provider/provider.dart';

class CheckoutSummaryCard extends StatefulWidget {
  const CheckoutSummaryCard({
    Key? key,
    this.cart,
  }) : super(key: key);

  final CartModel? cart;

  @override
  State<CheckoutSummaryCard> createState() => _CheckoutSummaryCardState();
}

class _CheckoutSummaryCardState extends State<CheckoutSummaryCard> {
  int order = 0;
  @override
  Widget build(BuildContext context) {
    final _counter = Provider.of<CounterModel>(context);
    return Row(
      children: [
        SizedBox(
          width: getProportionateScreenWidth(70)!,
          child: Column(
            children: [
              AspectRatio(
                aspectRatio: 0.88,
                child: Container(
                  // padding: EdgeInsets.all(
                  //   getProportionateScreenWidth(10)!,
                  // ),
                  decoration: BoxDecoration(
                    // color: const Color(0xFFF5F6F9),
                    borderRadius: BorderRadius.circular(15),
                  ),
                  child: ClipRRect(
                      borderRadius: BorderRadius.circular(10),
                      child: Image.network(
                        widget.cart!.foodImg!,
                        fit: BoxFit.fill,
                      )),
                ),
              ),
              CustomText(
                text: 'Restaurant Name',
                fontSize: 10,
              ),
            ],
          ),
        ),
        const SizedBox(width: 20),
        Container(
          width: getProportionateScreenWidth(80),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                widget.cart!.title!,
                style: TextStyle(
                    color: Colors.red,
                    fontSize: getProportionateScreenWidth(22)!,
                    fontWeight: FontWeight.bold),
                maxLines: 2,
              ),
              const SizedBox(height: 10),
              Row(
                crossAxisAlignment: CrossAxisAlignment.end,
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  InkWell(
                    onTap: () {
                      order > 0 ? decrement() : null;
                      _counter.totalSum >= 0 && order >= 0
                          ? _counter.decrement(price: widget.cart!.price!)
                          : null;
                      _counter.totalSum < 0 ? _counter.zero() : null;
                    },
                    child: Container(
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(5),
                          color: Colors.grey[200],
                        ),
                        child: const Icon(
                          Icons.remove,
                        )),
                  ),
                  SizedBox(width: 8,),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children: [
                      Text(
                        '$order',
                        style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: getProportionateScreenHeight(18)),
                      ),
                    ],
                  ),
                  SizedBox(width: 8,),
                  InkWell(
                    onTap: () {
                      increment(price: widget.cart!.price);
                      _counter.increment(price: widget.cart!.price!);
                    },
                    child: Container(
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(5),
                          color: Colors.grey[200],
                        ),
                        child: const Icon(
                          Icons.add,
                        )),
                  ),
                ],
              ),
            ],
          ),
        ),
        SizedBox(
          width: getProportionateScreenWidth(50)!,
        ),
        CustomText(
          text: 'Rs ${(widget.cart!.price!*order).toString()}',
          fontSize: 18,
          fontWeight: FontWeight.bold,
          color: Colors.blue
        ),
      ],
    );
  }

  void increment({int? price}) {
    setState(() {
      order++;
    });
  }

  void decrement({int? price}) {
    setState(() {
      order--;
    });
  }
}
