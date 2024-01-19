import 'package:flutter/material.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/presentation/onboarding_two_screen/onboarding_two_screen.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';

class OnboardingOneScreen extends StatelessWidget {
  const OnboardingOneScreen({Key? key})
      : super(
          key: key,
        );

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: SizedBox(
          width: double.maxFinite,
          child: Column(
            children: [
              CustomImageView(
                imagePath: ImageConstant.imgBackground,
                height: 460.v,
                width: 375.h,
              ),
              SizedBox(height: 62.v),
              Container(
                width: 363.h,
                margin: EdgeInsets.symmetric(horizontal: 6.h),
                child: Text(
                  "Porta il tuo allenamento ad un livello superiore:\n schede personalizzate, coach dedicati,\n successo garantito.",
                  maxLines: 3,
                  overflow: TextOverflow.ellipsis,
                  textAlign: TextAlign.center,
                  style: CustomTextStyles.bodyLargeAnton.copyWith(
                    height: 1.68,
                  ),
                ),
              ),
              SizedBox(height: 37.v),
              CustomElevatedButton(
                onPressed: () {
                  Navigator.of(context).push(
                    MaterialPageRoute(
                      builder: (context) => OnboardingTwoScreen(),
                    ),
                  );
                },
                width: 185.h,
                text: "Avanti",
                margin: EdgeInsets.only(left: 86.h),
                rightIcon: Container(
                  margin: EdgeInsets.only(left: 8.h),
                  child: CustomImageView(
                    imagePath: ImageConstant.imgChevronright,
                    height: 24.adaptSize,
                    width: 24.adaptSize,
                  ),
                ),
                alignment: Alignment.centerLeft,
              ),
              SizedBox(height: 38.v),
              SizedBox(
                height: 4.v,
                child: AnimatedSmoothIndicator(
                  activeIndex: 0,
                  count: 3,
                  effect: ScrollingDotsEffect(
                    spacing: 10,
                    activeDotColor: theme.colorScheme.primary,
                    dotColor: appTheme.gray800,
                    dotHeight: 4.v,
                    dotWidth: 16.h,
                  ),
                ),
              ),
              SizedBox(height: 5.v),
            ],
          ),
        ),
      ),
    );
  }
}
