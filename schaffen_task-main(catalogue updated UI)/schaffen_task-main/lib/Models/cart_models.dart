
import 'package:flutter/material.dart';

class CartModel {
  final String? foodImg;
  final int? price;
  final int? votes;
  final String? title;

  CartModel({this.foodImg, this.price, this.votes,this.title});
}

// Demo data for our cart

List<CartModel>? demoCarts = [
  CartModel(
    title:'Pizza',
    foodImg: "https://images.pexels.com/photos/315755/pexels-photo-315755.jpeg",
    price: 120,
    votes: 45,
  ),
  CartModel(
    title:'Burger',
    foodImg:
    "https://images.pexels.com/photos/1893557/pexels-photo-1893557.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
    price: 90,
    votes: 62,
  ),
  CartModel(
    title:'Dosa',
    foodImg:
    "https://images.pexels.com/photos/5560763/pexels-photo-5560763.jpeg?cs=srgb&dl=pexels-saveurs-secretes-5560763.jpg&fm=jpg",
    price: 145,
    votes: 105,
  ),
  CartModel(
    title:'Samosa',
    foodImg:
    "https://images.pexels.com/photos/2474658/pexels-photo-2474658.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
    price: 30,
    votes: 156,
  ),

];
