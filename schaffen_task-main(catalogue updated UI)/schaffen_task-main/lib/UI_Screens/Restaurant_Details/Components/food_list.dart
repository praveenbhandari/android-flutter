import 'package:flutter/material.dart';
import 'package:schaffen_task/Constants/ui.dart';
import 'package:schaffen_task/Models/restaurant_detail.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/add_button.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/Components/veg_portion.dart';

class FoodListView extends StatelessWidget {
  const FoodListView({
    Key? key,
    required this.title,
    required this.foods,
  }) : super(key: key);

  final String title;
  final List<RestaurantDetail>? foods;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 10.0, vertical: 10.0),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          UIHelper.verticalSpaceMedium(),
          Text(
            title,
            style:
            Theme.of(context).textTheme.subtitle2!.copyWith(fontSize: 18.0),
          ),
          ListView.builder(
            shrinkWrap: true,
            itemCount: foods==null?0:foods!.length,
            physics: const NeverScrollableScrollPhysics(),
            itemBuilder: (context, index) => Container(
              padding: const EdgeInsets.symmetric(vertical: 10.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  UIHelper.verticalSpaceSmall(),
                  Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      VegBadgeView(),
                      UIHelper.horizontalSpaceMedium(),
                      Expanded(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.stretch,
                          children: <Widget>[
                            Text(
                              foods![index].title,
                              style: Theme.of(context).textTheme.bodyText1,
                            ),
                            UIHelper.verticalSpaceSmall(),
                            Text(
                              foods![index].price,
                              style: Theme.of(context)
                                  .textTheme
                                  .bodyText1!
                                  .copyWith(fontSize: 14.0),
                            ),
                            UIHelper.verticalSpaceMedium(),
                            Text(
                              foods![index].desc,
                              maxLines: 2,
                              overflow: TextOverflow.ellipsis,
                              style: Theme.of(context)
                                  .textTheme
                                  .bodyText1!
                                  .copyWith(
                                fontSize: 12.0,
                                color: Colors.grey[500],
                              ),
                            ),
                          ],
                        ),
                      ),
                      AddBtnView()
                    ],
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
