import 'package:flutter/material.dart';



class CounterModel with ChangeNotifier {
  int _totalSum = 0;
 bool _isVeg=true;
  int get totalSum => _totalSum;
  bool get isVeg=>_isVeg;

  void zero()
  {
    _totalSum=0;
    notifyListeners();
  }
  void increment({int?price}) {
    _totalSum+=price!;
    notifyListeners();
  }
  void decrement({int?price}) {
    _totalSum-=price!;
    notifyListeners();
  }
  void change()
  {
    _isVeg=!_isVeg;
    notifyListeners();
  }
}



