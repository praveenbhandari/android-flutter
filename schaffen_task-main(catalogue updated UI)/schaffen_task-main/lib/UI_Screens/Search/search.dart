import 'package:flutter/material.dart';
import 'package:schaffen_task/Components/bottom_navigation.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/UI_Screens/Search/Components/body.dart';

class Search extends StatelessWidget {
  const Search({Key? key}) : super(key: key);
  static String routeName = "/search";
  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    return Scaffold(

      body: SearchScreen(),
      bottomNavigationBar: BottomNavigation(),
    );
  }
}
