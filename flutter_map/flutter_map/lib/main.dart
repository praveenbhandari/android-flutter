import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:location/location.dart';
import 'package:permission/permission.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  GoogleMapController mapController;
  Location location;
  LocationData currentlocation;
  Set<Marker> mark = {};
  Set<Circle> circle = {};
  Set<Polygon> polygon={};
  LatLng position;

  // PermissionName permissionName = PermissionName.Internet;

//   Future<void> loc() async {
// currentlocation=location.getLocation();
//    // Position currentlocation = await Geolocator.getCurrentPosition(desiredAccuracy: LocationAccuracy.high,timeLimit:Duration(seconds: 2) );
//     print("in location");
//     print("lat="+currentlocation.latitude.toString()+"  LON"+currentlocation.longitude.toString());
//   }

  // LatLng _center=new LatLng(0.0, 0.0);

  void initState()  {
    super.initState();
    print("ininit");
    location = new Location();
    current_location();
    on_location_changed();

  }
  void current_location() async {
    setState(() async {
      currentlocation = await location.getLocation();
      position = new LatLng(currentlocation.latitude, currentlocation.longitude);
    });

  }
  void on_location_changed(){
    location.onLocationChanged().listen((LocationData changelocation) {
      setState(() {
        currentlocation = changelocation;
        marke(new LatLng(currentlocation.latitude, currentlocation.longitude));
      });

    });
  }


  void _onMapCreated(GoogleMapController controller) async {
    mapController = controller;
    location = new Location();
    current_location();
    on_location_changed();

    //Position pos = await Geolocator.getCurrentPosition(desiredAccuracy: LocationAccuracy.high);
    print("in location");
    print("lat=" + currentlocation.latitude.toString() + "  LON" +currentlocation.longitude.toString());
    LatLng posi = new LatLng(currentlocation.latitude, currentlocation.longitude);
    // _center =posi;
    setState(() {
      marke(posi);

      // mapController.moveCamera(CameraUpdate.newCameraPosition(
      //     CameraPosition(target: posi, zoom: 15)));
    });
  }
 // void camera_position(){}

  void marke(LatLng pos) {
    mapController.moveCamera(CameraUpdate.newCameraPosition(
        CameraPosition(target: pos, zoom: 15)));
    print("marker set");
    mark.add(Marker(
      markerId: MarkerId("null"),
      position: pos,
    ));
    circle.add(Circle(circleId: CircleId("CIRCLE"),center: pos,radius: 1000,strokeColor: Colors.red ,strokeWidth: 10,visible: true,fillColor: Colors.orangeAccent ) );
    polygon.add(Polygon(polygonId: PolygonId("value"),fillColor: Colors.red )) ;
    print("circle added");
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        //floatingActionButton: FloatingActionButton(child:Text('get'),onPressed: () { loc() ;},),
        appBar: AppBar(
          title: Text('Maps Sample App'),
          backgroundColor: Colors.green[700],
        ),
        body: GoogleMap(
          onMapCreated: _onMapCreated,
          initialCameraPosition: CameraPosition(
            zoom: 11.0,
            target: new LatLng(0.0, 0.0),
          ),
          markers: mark,
        ),
      ),
    );
  }
}
