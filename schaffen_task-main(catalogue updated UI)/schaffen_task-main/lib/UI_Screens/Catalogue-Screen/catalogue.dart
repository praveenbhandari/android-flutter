import 'package:flutter/material.dart';
import 'package:schaffen_task/Components/appbar.dart';
import 'package:schaffen_task/Components/bottom_navigation.dart';
import 'package:schaffen_task/UI_Screens/Catalogue-Screen/Component/restaurant_grid.dart';

import '../Profile/profile.dart';
import '../Search/search.dart';

class CatalogueScreen extends StatefulWidget {
  const CatalogueScreen({Key? key}) : super(key: key);
  static String routeName = "/homepage";

  @override
  _CatalogueScreenState createState() => _CatalogueScreenState();
}

class _CatalogueScreenState extends State<CatalogueScreen> {
  List<String> cities = ["Mumbai", "Delhi", "Bangalore", "Chennai", "Kolkata"];
  String init_val = "Mumbai";

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        // backgroundColor: Color.fromRGBO(70, 70, 70, 50) ,
        extendBody: true,
        bottomNavigationBar: const Padding(
            padding: EdgeInsets.all(10.0), child: BottomNavigation()),
        body: Scaffold(
          body: Column(
            children: [
              Container(

                padding:
                    const EdgeInsets.only(left: 15.0, top: 2.0, bottom: 2.0),
                decoration: BoxDecoration(
                // color: Colors.black,
                  border: Border.all(color: Colors.grey[400]!),
                  borderRadius: BorderRadius.circular(2.0),
                ),
                child: Column(
                  children: [
                    Row(
                      children: [
                        // mainAxisAlignment: MainAxisAlignment.start, //change here don't //worked
                        // crossAxisAlignment: CrossAxisAlignment.center,

                        Row(
                          children: [
                            Icon(Icons.location_on_rounded,color: Colors.lightBlue,),
                            SizedBox(width: 5,),
                            DropdownButton(
                                alignment: Alignment.centerLeft,
                                value: init_val,
                                icon: const Icon(Icons.keyboard_arrow_down),
                                items: cities.map((String items) {
                                  return DropdownMenuItem(
                                    value: items,
                                    child: Text(items),
                                  );
                                }).toList(),
                                // After selecting the desired option,it will
                                // change button value to selected value
                                onChanged: (String? newValue) {
                                  setState(() {
                                    init_val = newValue!;
                                  });
                                }),
                          ],
                        ),
                        Spacer(),
                        GestureDetector(
                          onTap: () {
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => Profile()));
                          },
                          child: CircleAvatar(
                            radius: 20,
                            backgroundImage: NetworkImage(
                                "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                          ),
                        )
                      ],
                    ),
                    Row(
                      children: <Widget>[
                        Expanded(
                          child: GestureDetector(
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => Search()),
                              );
                            },
                            child: Text(
                              "Search for restaurants and food",style: TextStyle(color: Colors.grey,fontSize: 20),
                              // onTap: () => {
                              //   Navigator.push(
                              //     context,
                              //     MaterialPageRoute(
                              //         builder: (context) => Search()),
                              //   )
                              // },
                              //   decoration: InputDecoration(
                              //     hintText: 'Search for restaurants and food',
                              //     hintStyle: Theme.of(context)
                              //         .textTheme
                              //         .subtitle2!
                              //         .copyWith(
                              //           color: Colors.grey,
                              //           fontSize: 17.0,
                              //           fontWeight: FontWeight.w600,
                              //         ),
                              //     border: InputBorder.none,
                              //   ),
                            ),
                          ),
                        ),
                        // UIHelper.horizontalSpaceMedium(),
                        IconButton(
                          icon: const Icon(Icons.search),
                          onPressed: () {},
                        )
                      ],
                    ),
                  ],
                ),
              ),
              const RestaurantGrid(),
            ],
          ),
        ),
      ),
    );
  }
}
