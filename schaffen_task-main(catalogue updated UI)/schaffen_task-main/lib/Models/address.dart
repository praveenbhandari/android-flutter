
class Address {
  String name;
  String mobileNumber;
  String address;
  String ?city;
  Address(
      {required this.name,
      required this.address,
      required this.mobileNumber,
      this.city});
}
