
import 'package:flutter/material.dart';

import 'package:schaffen_task/UI_Screens/Cart/cart.dart';
import 'package:schaffen_task/UI_Screens/Catalogue-Screen/catalogue.dart';
import 'package:schaffen_task/UI_Screens/Checkout/checkout.dart';
import 'package:schaffen_task/UI_Screens/Deliver-Address/Components/previous_address.dart';
import 'package:schaffen_task/UI_Screens/Deliver-Address/address.dart';
import 'package:schaffen_task/UI_Screens/MyOrders/my_orders.dart';
import 'package:schaffen_task/UI_Screens/Order_Tracker/order_track.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/restaurant_detail.dart';
import 'package:schaffen_task/UI_Screens/log_in.dart';
import 'package:schaffen_task/UI_Screens/Profile/profile.dart';
import 'package:schaffen_task/UI_Screens/Search/search.dart';

final Map<String, WidgetBuilder> routes = {
  LogIn.routeName: (context) => const LogIn(),
  CartScreen.routeName: (context) => const CartScreen(),
  Checkout.routeName: (context) => const Checkout(),
  OrderTracker.routeName: (context) => const OrderTracker(),
  CatalogueScreen.routeName: (context) => const CatalogueScreen(),
  Profile.routeName: (context) => const Profile(),
  Search.routeName: (context) => const Search(),
  RestaurantDetails.routeName: (context) => const RestaurantDetails(),
  DeliAddress.routeName:(context)=>const DeliAddress(),
  PreviousAddress.routeName:(context)=>const PreviousAddress(),
  MyOrders.routeName:(context)=>const MyOrders(),
};
