import 'package:flutter/material.dart';
import 'package:schaffen_task/Components/button.dart';
import 'package:schaffen_task/Constants/size_config.dart';
import 'package:schaffen_task/UI_Screens/Catalogue-Screen/catalogue.dart';
import 'package:schaffen_task/UI_Screens/Deliver-Address/address.dart';
import 'package:flutter_signin_button/flutter_signin_button.dart';

class LogIn extends StatelessWidget {
  const LogIn({Key? key}) : super(key: key);
  static String routeName = "/log_in";
  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);
    return Scaffold(
      body: Center(
        child: SizedBox(
          height: SizeConfig.screenHeight! * 0.08,
          width: SizeConfig.screenWidth! * 0.6,
          child: SignInButton(
            Buttons.GoogleDark,
            onPressed: () {
              Navigator.pushNamed(
                context,
                CatalogueScreen.routeName,
              );
            },
          ),
        ),
      ),
    );
  }
}
