

import 'package:flutter/material.dart';

import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:location/location.dart';

import 'notification_flutter.dart';


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
    // current_location();
    on_location_changed();
  }


  void current_location() async {
    print("in current location");
    setState(() async {
      currentlocation = await location.getLocation();
      position = new LatLng(currentlocation.latitude, currentlocation.longitude);
      setState(() {
        print("current location marker");
        marke(position);
      });

    });
  }

  void on_location_changed() {
    print("On Location Change");
    location.onLocationChanged().listen((LocationData changelocation) {
      setState(() {
        currentlocation = changelocation;
        print("location change marker");

        marke(new LatLng(currentlocation.latitude, currentlocation.longitude));
        print("current location : "+currentlocation.latitude.toString()+","+currentlocation.longitude.toString());
      });
    });
  }

  void _onMapCreated(GoogleMapController controller) async {
    print("on map create");
    mapController = controller;
    location =  new Location();
    print("waiting for location");
     current_location();
  //  await on_location_changed();
    print("got location");
    print("LAT=" +currentlocation.latitude.toString() + "  LON" + currentlocation.longitude.toString());

    setState(() {
      print("mapcreate marker");
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
      MaterialApp(
      home: Scaffold(
        body:
      GoogleMap(
        onMapCreated: _onMapCreated,
        initialCameraPosition: CameraPosition(
          zoom: 11.0,
          target: new LatLng(0.0, 0.0),
        ),
        markers: mark,
        circles: circle,
          ),
        ),
      );
  }
}
