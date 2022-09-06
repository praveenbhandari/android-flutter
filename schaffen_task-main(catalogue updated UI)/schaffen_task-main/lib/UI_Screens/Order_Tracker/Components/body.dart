import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:schaffen_task/Components/button.dart';
import 'package:schaffen_task/Constants/ui.dart';
import 'package:schaffen_task/Provider/provider.dart';
import 'package:schaffen_task/UI_Screens/Order_Tracker/Components/order_summary.dart';
import 'package:timeline_tile/timeline_tile.dart';

class Body extends StatefulWidget {
  const Body({Key? key}) : super(key: key);

  @override
  _BodyState createState() => _BodyState();
}

class _BodyState extends State<Body> {
  bool OrderSummaryVisible = false;
  Color getOrderPlacedTimelineColor(String orderStatus) {
    if (orderStatus == 'Order Placed' ||
        orderStatus == 'On the way' ||
        orderStatus == 'Order delivered' ||
        orderStatus == 'Order canceled') {
      return Colors.green;
    } else {
      return Colors.grey;
    }
  }

  @override
  Widget build(BuildContext context) {
    final _counter = Provider.of<CounterModel>(context);
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.black,
          centerTitle: true,
          title: const Text(
            "Order Details",
            style: TextStyle(color: Colors.amber, fontSize: 25),
          ),
          shape: const RoundedRectangleBorder(
              borderRadius: BorderRadius.only(
                  bottomLeft: Radius.circular(25),
                  bottomRight: Radius.circular(25))),
        ),
        body: Container(
          padding: const EdgeInsets.all(8),
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisSize: MainAxisSize.min,
              children: [
                const SizedBox(
                  height: 20,
                ),
                const Text(
                  "Order Placed",
                  style: TextStyle(
                      color: Colors.green,
                      fontSize: 40,
                      fontWeight: FontWeight.bold),
                ),
                const SizedBox(
                  height: 20,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: const [
                    Text(
                      "Order Id :",
                      style: TextStyle(color: Colors.black, fontSize: 25),
                    ),
                    Text(
                      "23452345234536767",
                      style: TextStyle(
                          color: Colors.amber,
                          fontSize: 25,
                          fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: const [
                    Text(
                      "Order Date :",
                      style: TextStyle(color: Colors.black, fontSize: 25),
                    ),
                    Text(
                      "date",
                      style: TextStyle(
                          color: Colors.amber,
                          fontSize: 25,
                          fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 30,
                ),
                Container(
                  height: 300,
                  width: MediaQuery.of(context).size.width,
                  constraints: const BoxConstraints(maxHeight: 20),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Expanded(
                          child: Icon(Icons.shopping_basket,
                              size: 50,
                              color: getOrderPlacedTimelineColor(
                                  "Order delivered"))),
                      // if (orderStatus != 'Order canceled')
                      Expanded(
                          child: Icon(
                        Icons.directions_bike,
                        size: 50,
                        color: getOnTheWayTimelineColor("Order delivered"),
                      )),
                      Expanded(
                          child: Icon(Icons.home_filled,
                              size: 50,
                              color: getOrderdeliveredTimelineColor(
                                  "Order delivered"))),
                    ],
                  ),
                ),
                const SizedBox(
                  height: 30,
                ),
                Container(
                  height: 200,
                  width: MediaQuery.of(context).size.width,
                  constraints: const BoxConstraints(maxHeight: 20),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Expanded(
                        child: TimelineTile(
                          isFirst: true,
                          alignment: TimelineAlign.start,
                          axis: TimelineAxis.horizontal,
                          afterLineStyle: LineStyle(
                              color:
                                  getOnTheWayTimelineColor("Order delivered")),
                          indicatorStyle: IndicatorStyle(
                              color: getOrderPlacedTimelineColor(
                                  "Order delivered"),
                              indicatorXY: 0.4,
                              padding: const EdgeInsets.only(right: 10)),
                        ),
                      ),
                      // if (orderStatus != 'Order canceled')
                      Expanded(
                        child: TimelineTile(
                          alignment: TimelineAlign.center,
                          axis: TimelineAxis.horizontal,
                          beforeLineStyle: LineStyle(
                              color:
                                  getOnTheWayTimelineColor("Order delivered")),
                          afterLineStyle: LineStyle(
                              color: getOrderdeliveredTimelineColor(
                                  "Order delivered")),
                          indicatorStyle: IndicatorStyle(
                              color:
                                  getOnTheWayTimelineColor("Order delivered"),
                              indicatorXY: .45,
                              padding:
                                  const EdgeInsets.only(left: 10, right: 10)),
                        ),
                      ),
                      Expanded(
                        child: TimelineTile(
                          isLast: true,
                          alignment: TimelineAlign.end,
                          axis: TimelineAxis.horizontal,
                          beforeLineStyle: LineStyle(
                              color: getOrderdeliveredTimelineColor(
                                  "Order delivered")),
                          indicatorStyle: IndicatorStyle(
                              color: getOrderdeliveredTimelineColor(
                                  "Order delivered"),
                              indicatorXY: .5,
                              padding: const EdgeInsets.only(left: 10)),
                        ),
                      ),
                    ],
                  ),
                ),
                const SizedBox(
                  height: 30,
                ),
                SizedBox(
                  height: 50,
                  width: MediaQuery.of(context).size.width,
                  child: CustomRaisedButton(
                    shadowColor: Colors.amber,
                    backcolor: Colors.black,
                    text: 'Cancel Now',
                    pressed: null,
                    radius: 20,
                  ),
                ),
                const SizedBox(
                  height: 30,
                ),
                Center(
                  child: SizedBox(
                    height: 50,
                    child: ElevatedButton(

                      onPressed: (){
                        setState(() {
                          OrderSummaryVisible = !OrderSummaryVisible;
                        });
                      },


                      style: ButtonStyle(
                        padding: MaterialStateProperty.all(EdgeInsets.fromLTRB(20, 0, 20, 0)),
                        shadowColor: MaterialStateProperty.all(Colors.amber),
                        backgroundColor: MaterialStateProperty.all(Colors.black),
                        shape: MaterialStateProperty.all(
                          RoundedRectangleBorder(borderRadius: BorderRadius.circular(20),),),
                      ),
                      child:  Text(
                        'Order Summary',
                        style: TextStyle(
                          color: Colors.amber,
                          fontSize: 25,
                        ),
                      ),
                    ),
                  ),
                ),
                const SizedBox(
                  height: 30,
                ),
                Visibility(
                  visible: OrderSummaryVisible,
                  child:  Center(
                    child: BillDetailView(deliverFee: 35,taxes: 12,total: _counter.totalSum.toDouble(),),
                  ),
                ),
              ],
            ),
          ),
        ));
  }



}
