import 'package:flutter/material.dart';

import 'divider.dart';

class UIHelper {
  static const double _verticalSpaceExtraSmall = 4.0;
  static const double _verticalSpaceSmall = 8.0;
  static const double _verticalSpaceMedium = 16.0;
  static const double _verticalSpaceLarge = 24.0;
  static const double _verticalSpaceExtraLarge = 48;

  static const double _horizontalSpaceExtraSmall = 4;
  static const double _horizontalSpaceSmall = 8.0;
  static const double _horizontalSpaceMedium = 16.0;
  static const double _horizontalSpaceLarge = 24.0;
  static const double _horizontalSpaceExtraLarge = 48.0;

  static SizedBox verticalSpaceExtraSmall() =>
      verticalSpace(_verticalSpaceExtraSmall);
  static SizedBox verticalSpaceSmall() => verticalSpace(_verticalSpaceSmall);
  static SizedBox verticalSpaceMedium() => verticalSpace(_verticalSpaceMedium);
  static SizedBox verticalSpaceLarge() => verticalSpace(_verticalSpaceLarge);
  static SizedBox verticalSpaceExtraLarge() =>
      verticalSpace(_verticalSpaceExtraLarge);

  static SizedBox verticalSpace(double height) => SizedBox(height: height);

  static SizedBox horizontalSpaceExtraSmall() =>
      horizontalSpace(_horizontalSpaceExtraSmall);
  static SizedBox horizontalSpaceSmall() =>
      horizontalSpace(_horizontalSpaceSmall);
  static SizedBox horizontalSpaceMedium() =>
      horizontalSpace(_horizontalSpaceMedium);
  static SizedBox horizontalSpaceLarge() =>
      horizontalSpace(_horizontalSpaceLarge);
  static SizedBox horizontalSpaceExtraLarge() =>
      horizontalSpace(_horizontalSpaceExtraLarge);

  static SizedBox horizontalSpace(double width) => SizedBox(width: width);
}


Color getOnTheWayTimelineColor(String orderStatus) {
  if (orderStatus == 'On the way' || orderStatus == 'Order delivered')
    return Colors.green;
  else if (orderStatus == 'Order canceled')
    return Colors.red;
  else
    return Colors.grey;
}

Color getOrderdeliveredTimelineColor(String orderStatus) {
  if (orderStatus == 'Order delivered')
    return Colors.green;
  else if (orderStatus == 'Order canceled')
    return Colors.red;
  else
    return Colors.grey;
}
CustomDividerView buildDivider() => CustomDividerView(
  dividerHeight: 1.0,
  color: Colors.grey[400],
);
Expanded buildVerticalStack(
    BuildContext context, String title, String subtitle) =>
    Expanded(
      child: SizedBox(
        height: 60.0,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              title,
              style: Theme.of(context)
                  .textTheme
                  .subtitle2!
                  .copyWith(fontSize: 15.0),
            ),
            UIHelper.verticalSpaceExtraSmall(),
            Text(subtitle,
                style: Theme.of(context)
                    .textTheme
                    .bodyText1!
                    .copyWith(fontSize: 13.0))
          ],
        ),
      ),
    );

