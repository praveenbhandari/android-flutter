import 'package:flutter/material.dart';
import 'package:schaffen_task/UI_Screens/Cart/cart.dart';

import 'package:schaffen_task/UI_Screens/Catalogue-Screen/catalogue.dart';
import 'package:schaffen_task/UI_Screens/Profile/profile.dart';
import 'package:schaffen_task/UI_Screens/Search/search.dart';

int _current_index = 0;
class BottomNavigation extends StatefulWidget {
  const BottomNavigation({Key? key}) : super(key: key);

  @override
  State<BottomNavigation> createState() => _BottomNavigationState();
}

class _BottomNavigationState extends State<BottomNavigation> {

  List<String> Routes = [
    CatalogueScreen.routeName,
    Search.routeName,
    CartScreen.routeName,
    Profile.routeName,
  ];
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(7),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: Colors.black,
      ),
      child: BottomNavigationBar(
        selectedItemColor: Colors.amberAccent,
        type: BottomNavigationBarType.fixed,
        backgroundColor: Colors.black,
        unselectedItemColor: Colors.white,
        selectedFontSize: 10,
        showUnselectedLabels: false,

        // selectedIconTheme: const IconThemeData(opacity: 100),
        // unselectedIconTheme:const IconThemeData(opacity: 90) ,
        // selectedLabelStyle: TextStyle( ),
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.home_outlined),
            label: "Home",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.search),
            label: "Search",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.shopping_cart),
            label: "Cart",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.account_circle_outlined),
            label: "Account",
          ),
        ],
        currentIndex: _current_index,
        onTap: tapped,
      ),
    );
  }

  void tapped(int index) {
    setState(() {
      _current_index = index;
    });
    Navigator.pushNamed(
      context,
      Routes[index],
    );
  }
}
