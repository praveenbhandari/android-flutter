import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'package:schaffen_task/Constants/divider.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/Constants/ui.dart';
import 'package:schaffen_task/Models/restaurant_detail.dart';
import 'package:schaffen_task/Provider/provider.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/food_list.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/recommended_food.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/toggle.dart';


class Body extends StatefulWidget {
  const Body({Key? key}) : super(key: key);

  @override
  _BodyState createState() => _BodyState();
}

class _BodyState extends State<Body> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        iconTheme: const IconThemeData(color: Colors.black),
        elevation: 0.0,
        actions: <Widget>[
          const Icon(Icons.favorite_border),
          UIHelper.horizontalSpaceSmall(),
          const Icon(Icons.search),
          UIHelper.horizontalSpaceSmall(),
        ],
      ),
      body: _OrderNowView(),
    );
  }
}

class _OrderNowView extends StatefulWidget {
  @override
  State<_OrderNowView> createState() => _OrderNowViewState();
}

class _OrderNowViewState extends State<_OrderNowView> {
  bool? isVeg = true;

  @override
  Widget build(BuildContext context) {
    final _counter = Provider.of<CounterModel>(context);
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.all(15.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text(
                  'Namma Veedu Vasanta Bhavan',
                  style: Theme
                      .of(context)
                      .textTheme
                      .subtitle2!
                      .copyWith(fontWeight: FontWeight.bold, fontSize: 16.0),
                ),
                UIHelper.verticalSpaceSmall(),
                Text('South Indian',
                    style: Theme
                        .of(context)
                        .textTheme
                        .bodyText1),
                UIHelper.verticalSpaceExtraSmall(),
                Text('Velachery Main Road, Madipakkam',
                    style: Theme
                        .of(context)
                        .textTheme
                        .bodyText1),
                UIHelper.verticalSpaceMedium(),
                const CustomDividerView(dividerHeight: 1.0),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    buildVerticalStack(context, '4.1', 'Packaging 80%'),
                    buildVerticalStack(context, '29 mins', 'Delivery Time'),
                    buildVerticalStack(context, 'Rs150', 'For Two'),
                  ],
                ),
                const CustomDividerView(dividerHeight: 1.0),
                // UIHelper.verticalSpaceMedium(),
                // Column(
                //   children: <Widget>[
                //     _buildOfferTile(
                //         context, '30% off up to Rs75 | Use code SWIGGYIT'),
                //     _buildOfferTile(context,
                //         '20% off up to Rs100 with SBI credit cards, once per week | Use code 100SBI')
                //   ],
                // ),
                // UIHelper.verticalSpaceSmall(),
              ],
            ),
          ),
          const CustomDividerView(dividerHeight: 15.0),
          Container(
            height: 80.0,
            padding: const EdgeInsets.all(10.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Expanded(
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      const Icon(
                        Icons.filter_vintage,
                        color: Colors.green,
                        size: 12.0,
                      ),
                      UIHelper.horizontalSpaceExtraSmall(),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          Text(
                            _counter.isVeg ? 'PURE VEG' : 'NON VEG',
                            style: Theme
                                .of(context)
                                .textTheme
                                .subtitle2!
                                .copyWith(
                                fontWeight: FontWeight.bold,
                                fontSize: 16.0),
                          ),
                          SizedBox(width: getProportionateScreenWidth(170),),
                          AnimatedSwitch(

                          ),
                        ],
                      )
                    ],
                  ),
                ),
                const CustomDividerView(dividerHeight: 0.5, color: Colors.black)
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(10.0),
            child: Text(
              'Recommended',
              style: Theme
                  .of(context)
                  .textTheme
                  .subtitle2!
                  .copyWith(fontSize: 18.0),
            ),
          ),
          RecommendedFoodView(),
          const CustomDividerView(dividerHeight: 15.0),
          FoodListView(
            title: 'Breakfast',
            foods: _counter.isVeg ? RestaurantDetail.getBreakfast() : null,
          ),
          const CustomDividerView(dividerHeight: 15.0),
          FoodListView(
            title: 'All Time Favourite',
            foods: _counter.isVeg
                ? RestaurantDetail.getAllTimeFavFoods()
                : null,
          ),
          const CustomDividerView(dividerHeight: 15.0),
          FoodListView(
            title: 'Kozhukattaiyum & Paniyarams',
            foods: _counter.isVeg ? RestaurantDetail.getOtherDishes() : null,
          )
        ],
      ),
    );
  }

}


