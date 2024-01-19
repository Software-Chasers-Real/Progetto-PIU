import 'package:flutter/material.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/presentation/onboarding_three_screen/onboarding_three_screen.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';

class OnboardingTwoScreen extends StatelessWidget {
  const OnboardingTwoScreen({Key? key})
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
                imagePath: ImageConstant.imgBackground460x375,
                height: 460.v,
                width: 375.h,
              ),
              SizedBox(height: 61.v),
              Container(
                width: 341.h,
                margin: EdgeInsets.symmetric(horizontal: 17.h),
                child: Text(
                  "Realizza i tuoi obiettivi di fitness con facilità:\n gestisci allenamenti, esercizi e coach,\n tutto in un'unica app.",
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
                      builder: (context) => OnboardingThreeScreen(),
                    ),
                  );
                },
                width: 185.h,
                text: "Avanti",
                rightIcon: Container(
                  margin: EdgeInsets.only(left: 8.h),
                  child: CustomImageView(
                    imagePath: ImageConstant.imgChevronright,
                    height: 24.adaptSize,
                    width: 24.adaptSize,
                  ),
                ),
              ),
              SizedBox(height: 38.v),
              SizedBox(
                height: 4.v,
                child: AnimatedSmoothIndicator(
                  activeIndex: 1,
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
