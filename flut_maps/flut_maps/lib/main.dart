
import 'package:flut_maps/notification_flutter.dart';
import 'package:flutter/material.dart';

import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:location/location.dart';


void main()  {
  WidgetsFlutterBinding.ensureInitialized();

  runApp(
      MaterialApp(
        initialRoute: 'notification',
        routes: {
          'notification':(context) => noti()
        },
        home: Scaffold(
          body: loca(),
        ),
      ),);
}


class loca extends StatefulWidget {
  @override
  _locaState createState() => _locaState();
}

class _locaState extends State<loca> {
  GoogleMapController mapController;
  Location location;
  LocationData currentlocation;
  Set<Marker> mark = {};
  Set<Circle> circle = {};
  Set<Polygon> polygon = {};
  LatLng position;

  void initState() {
    super.initState();

    print("ininit");
    location = new Location();
    current_location();
    on_location_changed();
  }


  void current_location() async {
    print("in current location");
    setState(() async {
      currentlocation = await location.getLocation();
      position =
          new LatLng(currentlocation.latitude, currentlocation.longitude);
      marke(position);
    });
  }

  void on_location_changed() {
    print("On Location Change");
    location.onLocationChanged().listen((LocationData changelocation) {
      setState(() {
        currentlocation = changelocation;
        marke(new LatLng(currentlocation.latitude, currentlocation.longitude));
      });
    });
  }

  void _onMapCreated(GoogleMapController controller) async {
    print("on map create");
    mapController = controller;
    location = new Location();
    print("waiting for location");
    await current_location();
    print("got location");
    on_location_changed();
    // GeoFirePoint center = geo.point(latitude: currentlocation.latitude, longitude: currentlocation.longitude);
    // var collectionref= _firestore.collection('location');
    // geo.collection(collectionRef: collectionref).within(center: center, radius: 500, field: 'position');
    // print("addef geo");
    // print(geo);
     print("lat=" +
        currentlocation.latitude.toString() +
        "  LON" +
        currentlocation.longitude.toString());

    setState(() {
      marke(position);
    });
  }

  void marke(LatLng pos) {

    // print(geo.collection(collectionRef: null));
    mapController.animateCamera(
        CameraUpdate.newCameraPosition(CameraPosition(target: pos, zoom: 15)));
    // mapController.moveCamera(CameraUpdate.newCameraPosition(
    //     CameraPosition(target: pos, zoom: 15)));
    print("marker set");
    mark.add(Marker(
      markerId: MarkerId("Me"),
      position: pos,
    ));
    circle.add(Circle(
        circleId: CircleId("CIRCLE"),
        center: pos,
        radius: 500,
        strokeColor: Colors.red,
        strokeWidth: 3,
        fillColor: Colors.orangeAccent.withOpacity(0.5)));
    print("circle added");
  }

  @override
  Widget build(BuildContext context) {
    return
      // MaterialApp(
      // home: Scaffold(
      //   body:
         GoogleMap(
          onMapCreated: _onMapCreated,
          initialCameraPosition: CameraPosition(
            zoom: 11.0,
            target: new LatLng(0.0, 0.0),
          ),
          markers: mark,
          circles: circle,
      //   ),
      // ),
    );
  }
}
