class ProfileModel
{
  String? name;
  String? phoneNo;
  String? address;

  ProfileModel({this.name, this.phoneNo, this.address});
}

List<ProfileModel>Profiles=[
  ProfileModel(
    name: 'Sanskar Modi',
    phoneNo: '7894561234',
    address: 'Vikas Nagar, Lucknow'
  ),
];