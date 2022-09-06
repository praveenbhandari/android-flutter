import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/Models/cart_models.dart';
import 'package:schaffen_task/Provider/provider.dart';

class CartCard extends StatefulWidget {
  const CartCard({
    Key? key,
    this.cart,
  }) : super(key: key);

  final CartModel? cart;

  @override
  State<CartCard> createState() => _CartCardState();
}

class _CartCardState extends State<CartCard> {
  int order=0;
  @override
  Widget build(BuildContext context) {
    final _counter = Provider.of<CounterModel>(context);
    return Row(
      children: [
        SizedBox(
          width: getProportionateScreenWidth(90)!,
          child: AspectRatio(
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
        ),
        const SizedBox(width: 20),
        Container(
          width: getProportionateScreenWidth(80),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                widget.cart!.title!,
                style:  TextStyle(color: Colors.black, fontSize: getProportionateScreenWidth(18)!,fontWeight: FontWeight.bold),
                maxLines: 2,
              ),
              const SizedBox(height: 10),
              Text.rich(
                TextSpan(
                  text: "Rs ${widget.cart!.price}",
                  style: const TextStyle(
                      fontWeight: FontWeight.w700, color: Colors.orange),
                  children: [
                    TextSpan(
                        text: "  x ${order}",
                        style: Theme.of(context).textTheme.bodyText1),
                  ],
                ),
              ),
              const SizedBox(height: 10),
              FittedBox(
                child: Row(
                  children: [
                    const Icon(
                      Icons.thumb_up,
                      color: Colors.blueAccent,
                      size: 18,
                    ),
                    SizedBox(
                      width: getProportionateScreenWidth(10),
                    ),
                    Text(
                      '${widget.cart!.votes!}',
                      style: const TextStyle(
                        color: Colors.redAccent,
                        fontWeight: FontWeight.w700,
                      ),
                    )
                  ],
                ),
              ),
            ],
          ),
        ),
        SizedBox(
          width: getProportionateScreenWidth(80)!,
        ),
        Column(
          crossAxisAlignment: CrossAxisAlignment.end,
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            InkWell(
              onTap: (){
                increment(price: widget.cart!.price);
                _counter.increment(price:widget.cart!.price!);
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
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Text(
                  '$order',
                  style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: getProportionateScreenHeight(18)),
                ),
                SizedBox(
                  width: getProportionateScreenWidth(40),
                ),
              ],
            ),
            InkWell(
              onTap: ()
              {
                order>0?decrement(): null;
                _counter.totalSum>=0&&order>=0?_counter.decrement(price: widget.cart!.price!):null;
                _counter.totalSum<0?_counter.zero():null;
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
          ],
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
