class ProductData {
  final String i_name;
  final String i_image;
  double i_price;
  double i_rate;
  double i_cost;
  int itemcount;

  ProductData(
      {required this.i_name,
        required this.i_image,
      required this.i_price,
      required this.i_rate,
      this.i_cost = 0,
      this.itemcount = 1,
});
}
