import 'package:flutter/material.dart';
import 'package:flutter_staggered_grid_view/flutter_staggered_grid_view.dart';
import 'package:schaffen_task/Components/custom_text.dart';
import 'package:schaffen_task/Components/rating.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:schaffen_task/Models/restro_data.dart';
import 'package:schaffen_task/UI_Screens/Restaurant_Details/restaurant_detail.dart';


class RestaurantGrid extends StatefulWidget {
  const RestaurantGrid({Key? key}) : super(key: key);

  @override
  _RestaurantGridState createState() => _RestaurantGridState();
}

class _RestaurantGridState extends State<RestaurantGrid> {
  final _firebase = FirebaseFirestore.instance;
  @override
  Widget build(BuildContext context) {
    return FutureBuilder<QuerySnapshot>(
      future: _firebase.collection('data').get(),
      builder: (context, snapshot) {
        if (snapshot.hasError) {
          return const Center(
              child: Text(
            'Something went wrong',
         
          ));
        }

        if (snapshot.hasData && !snapshot.data!.docs.isNotEmpty) {
          return const Text("Error");
        }

        if (snapshot.connectionState == ConnectionState.done) {
          return StaggeredGridView.countBuilder(
            shrinkWrap: true,
            padding: const EdgeInsets.fromLTRB(5, 5, 5, 80),
            crossAxisCount: 4,
            itemCount: snapshot.data!.docs.length,
            itemBuilder: (BuildContext context, int index) {
              DocumentSnapshot data = snapshot.data!.docs[index];
              RestroData restroData = RestroData(
                  r_name: data['r_name'],
                  r_addr: data['r_addr'],
                  r_image: data['r_image'],
                  r_rating: data['r_rating'],
                  non_veg: data['r_menu']['non_veg'],
                  veg: data['r_menu']['veg']);
              return GestureDetector(
                onTap: () {
                  Navigator.pushNamed(context, RestaurantDetails.routeName);
                },
                child: Container(
                  child: Card(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20),
                    ),
                    color: Colors.black,
                    child: Column(
                      children: <Widget>[
                        ClipRRect(
                            borderRadius: BorderRadius.circular(10),
                            child: Image.network(restroData.r_image)),
                        Container(
                          padding: const EdgeInsets.all(5),
                          child: Column(
                            children: [
                              CustomText(
                                  text: restroData.r_name,
                                  fontSize: 15,
                                  color: Colors.amberAccent,
                                  position: TextAlign.left),
                              CustomText(
                                  text: restroData.r_addr,
                                  fontSize: 17,
                                  color: Colors.amberAccent,
                                  position: TextAlign.left),
                              CustomRatingBar(rating: restroData.r_rating),
                            ],
                          ),
                        ),
                      ],
                    ),
                    elevation: 20,
                  ),
                ),
              );
            },
            staggeredTileBuilder: (int index) => new StaggeredTile.fit(2),
            mainAxisSpacing: 4.0,
            crossAxisSpacing: 4.0,
          );
        }
          return const Center(
                  child: CircularProgressIndicator(
                    color: Colors.red,
                  ),
                );
      },
    );
  }
}
