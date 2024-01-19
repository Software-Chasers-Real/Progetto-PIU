import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/presentation/splash_screen/splash_screen.dart';
import 'package:vero_progetto_piu/presentation/onboarding_one_screen/onboarding_one_screen.dart';
import 'package:vero_progetto_piu/presentation/onboarding_two_screen/onboarding_two_screen.dart';
import 'package:vero_progetto_piu/presentation/onboarding_three_screen/onboarding_three_screen.dart';
import 'package:vero_progetto_piu/presentation/login_screen/login_screen.dart';
import 'package:vero_progetto_piu/presentation/login_two_screen/login_two_screen.dart';
import 'package:vero_progetto_piu/presentation/sign_up_screen/sign_up_screen.dart';
import 'package:vero_progetto_piu/presentation/sign_up_two_screen/sign_up_two_screen.dart';
import 'package:vero_progetto_piu/presentation/forgot_password_screen/forgot_password_screen.dart';
import 'package:vero_progetto_piu/presentation/verification_screen/verification_screen.dart';
import 'package:vero_progetto_piu/presentation/gender_screen/gender_screen.dart';
import 'package:vero_progetto_piu/presentation/gender_two_screen/gender_two_screen.dart';
import 'package:vero_progetto_piu/presentation/age_screen/age_screen.dart';
import 'package:vero_progetto_piu/presentation/weight_screen/weight_screen.dart';
import 'package:vero_progetto_piu/presentation/height_screen/height_screen.dart';
import 'package:vero_progetto_piu/presentation/goal_screen/goal_screen.dart';
import 'package:vero_progetto_piu/presentation/activity_level_screen/activity_level_screen.dart';
import 'package:vero_progetto_piu/presentation/gym_list_screen/gym_list_screen.dart';
import 'package:vero_progetto_piu/presentation/gym_plane_screen/gym_plane_screen.dart';
import 'package:vero_progetto_piu/presentation/app_navigation_screen/app_navigation_screen.dart';

class AppRoutes {
  static const String splashScreen = '/splash_screen';

  static const String onboardingOneScreen = '/onboarding_one_screen';

  static const String onboardingTwoScreen = '/onboarding_two_screen';

  static const String onboardingThreeScreen = '/onboarding_three_screen';

  static const String loginScreen = '/login_screen';

  static const String loginTwoScreen = '/login_two_screen';

  static const String signUpScreen = '/sign_up_screen';

  static const String signUpTwoScreen = '/sign_up_two_screen';

  static const String forgotPasswordScreen = '/forgot_password_screen';

  static const String verificationScreen = '/verification_screen';

  static const String genderScreen = '/gender_screen';

  static const String genderTwoScreen = '/gender_two_screen';

  static const String ageScreen = '/age_screen';

  static const String weightScreen = '/weight_screen';

  static const String heightScreen = '/height_screen';

  static const String goalScreen = '/goal_screen';

  static const String activityLevelScreen = '/activity_level_screen';

  static const String gymListScreen = '/gym_list_screen';

  static const String gymPlaneScreen = '/gym_plane_screen';

  static const String appNavigationScreen = '/app_navigation_screen';

  static Map<String, WidgetBuilder> routes = {
    splashScreen: (context) => SplashScreen(),
    onboardingOneScreen: (context) => OnboardingOneScreen(),
    onboardingTwoScreen: (context) => OnboardingTwoScreen(),
    onboardingThreeScreen: (context) => OnboardingThreeScreen(),
    loginScreen: (context) => LoginScreen(),
    loginTwoScreen: (context) => LoginTwoScreen(),
    signUpScreen: (context) => SignUpScreen(),
    signUpTwoScreen: (context) => SignUpTwoScreen(),
    forgotPasswordScreen: (context) => ForgotPasswordScreen(),
    verificationScreen: (context) => VerificationScreen(),
    genderScreen: (context) => GenderScreen(),
    genderTwoScreen: (context) => GenderTwoScreen(),
    ageScreen: (context) => AgeScreen(),
    weightScreen: (context) => WeightScreen(),
    heightScreen: (context) => HeightScreen(),
    goalScreen: (context) => GoalScreen(),
    activityLevelScreen: (context) => ActivityLevelScreen(),
    gymListScreen: (context) => GymListScreen(),
    gymPlaneScreen: (context) => GymPlaneScreen(),
    appNavigationScreen: (context) => AppNavigationScreen()
  };
}
