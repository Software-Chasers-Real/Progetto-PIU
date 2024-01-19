import 'dart:async';

import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/presentation/onboarding_one_screen/onboarding_one_screen.dart';

class SplashScreen extends StatelessWidget {
  const SplashScreen({Key? key})
      : super(
          key: key,
        );

  @override
  Widget build(BuildContext context) {
    // Add a Timer to navigate after 5 seconds
    Timer(
      Duration(seconds: 5),
      () {
        Navigator.of(context).pushReplacement(
          MaterialPageRoute(
            builder: (context) =>
                OnboardingOneScreen(), // Navigate to Test2 class
          ),
        );
      },
    );
    return SafeArea(
      child: Scaffold(
        body: SizedBox(
          width: double.maxFinite,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              SizedBox(height: 43.v),
              CustomImageView(
                imagePath: ImageConstant.imgMaskGroup,
                height: 116.v,
                width: 117.h,
              ),
              SizedBox(height: 53.v),
              SizedBox(
                width: 240.h,
                child: Text(
                  "FitnessApp",
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  textAlign: TextAlign.center,
                  style: CustomTextStyles.displayMediumNicoMojiPrimary,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
