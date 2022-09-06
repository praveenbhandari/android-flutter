import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:schaffen_task/Components/rating.dart';
import 'package:schaffen_task/Constants/ui.dart';
import 'package:schaffen_task/Models/restaurant_detail.dart';
import 'package:schaffen_task/Provider/provider.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/add_button.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/veg_portion.dart';

class RecommendedFoodView extends StatefulWidget {
  const RecommendedFoodView({Key? key}) : super(key: key);



  @override
  State<RecommendedFoodView> createState() => _RecommendedFoodViewState();
}

class _RecommendedFoodViewState extends State<RecommendedFoodView> {
  bool? isAdd=false;
  @override
  Widget build(BuildContext context) {
    final _counter = Provider.of<CounterModel>(context);
    final foods =  _counter.isVeg?RestaurantDetail.getBreakfast():RestaurantDetail.nonVegDish();
    return Container(
      padding: const EdgeInsets.all(10.0),
      child: GridView.count(
        shrinkWrap: true,
        crossAxisCount: 2,
        childAspectRatio: 0.8,
        physics: const NeverScrollableScrollPhysics(),
        children: List.generate(
          foods.length,
              (index) => Container(
            margin: const EdgeInsets.all(10.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: <Widget>[
                Expanded(
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(20),
                    child: Image.asset(
                      foods[index].image,
                      fit: BoxFit.fill,
                    ),
                  ),
                ),
                UIHelper.verticalSpaceExtraSmall(),
                SizedBox(
                  height: 90.0,
                  child: Column(
                    mainAxisSize: MainAxisSize.max,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: [
                          Text(
                            'BREAKFAST',
                            style:
                            Theme.of(context).textTheme.bodyText1!.copyWith(
                              fontSize: 10.0,
                              color: Colors.grey[700],
                            ),
                          ),
                          CustomRatingBar(rating: 4, size: 14),
                        ],
                      ),
                      UIHelper.verticalSpaceExtraSmall(),
                      Row(
                        children: <Widget>[
                           VegBadgeView(),
                          UIHelper.horizontalSpaceExtraSmall(),
                          Flexible(
                            child: Text(
                              foods[index].title,
                              maxLines: 1,
                              style:
                              const TextStyle(fontWeight: FontWeight.bold),
                            ),
                          ),
                        ],
                      ),
                      UIHelper.verticalSpaceMedium(),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Text(foods[index].price,
                              style: Theme.of(context)
                                  .textTheme
                                  .bodyText1!
                                  .copyWith(fontSize: 14.0)),
                          InkWell(
                              onTap: (){
                                setState(() {
                                  isAdd=!isAdd!;
                                });

                              },
                              child: AddBtnView(
                                isAdd: isAdd!,
                              ))
                        ],
                      )
                    ],
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}

