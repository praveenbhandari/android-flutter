import 'address.dart';
import 'product_data.dart';

class OrderData {
  String orderId;
  String orderTime;
  List<ProductData> products = [];
  String orderStatus;
  String orderValue;
  Address address;
  String totalAmount;
  String discount;
  String? remark;
  OrderData(
      {required this.orderId,
      required this.products,
      required this.orderTime,
      required this.orderStatus,
      required this.address,
      required this.orderValue,
      required this.totalAmount,
      required this.discount,
       this.remark,
});
}
