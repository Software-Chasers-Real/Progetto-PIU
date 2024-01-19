import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';
import 'package:vero_progetto_piu/widgets/custom_icon_button.dart';

class HeightScreen extends StatelessWidget {
  const HeightScreen({Key? key})
      : super(
          key: key,
        );

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Container(
          width: double.maxFinite,
          padding: EdgeInsets.symmetric(
            horizontal: 17.h,
            vertical: 44.v,
          ),
          child: Column(
            children: [
              SizedBox(height: 29.v),
              Text(
                "Quanto sei alto?",
                style: theme.textTheme.headlineMedium,
              ),
              SizedBox(height: 5.v),
              Text(
                "Questo aiuta i coach a creare il tuo piano personalizzato\r",
                style: theme.textTheme.bodyMedium,
              ),
              Spacer(
                flex: 49,
              ),
              SizedBox(
                height: 135.v,
                width: 74.h,
                child: Stack(
                  alignment: Alignment.topRight,
                  children: [
                    Align(
                      alignment: Alignment.bottomCenter,
                      child: Text(
                        "166",
                        style: theme.textTheme.displayMedium,
                      ),
                    ),
                    Align(
                      alignment: Alignment.topRight,
                      child: Padding(
                        padding: EdgeInsets.only(
                          top: 32.v,
                          right: 3.h,
                        ),
                        child: Text(
                          "165",
                          style: theme.textTheme.displaySmall,
                        ),
                      ),
                    ),
                    Align(
                      alignment: Alignment.topRight,
                      child: Padding(
                        padding: EdgeInsets.only(right: 10.h),
                        child: Text(
                          "164",
                          style: CustomTextStyles.headlineMediumOpenSansGray800,
                        ),
                      ),
                    ),
                  ],
                ),
              ),
              SizedBox(height: 1.v),
              SizedBox(
                width: 156.h,
                child: Divider(),
              ),
              SizedBox(height: 1.v),
              SizedBox(
                height: 79.v,
                width: 156.h,
                child: Stack(
                  alignment: Alignment.bottomRight,
                  children: [
                    Align(
                      alignment: Alignment.bottomCenter,
                      child: SizedBox(
                        width: 156.h,
                        child: Divider(),
                      ),
                    ),
                    Align(
                      alignment: Alignment.bottomRight,
                      child: Padding(
                        padding: EdgeInsets.only(
                          right: 2.h,
                          bottom: 12.v,
                        ),
                        child: Text(
                          "cm",
                          style: theme.textTheme.bodyLarge,
                        ),
                      ),
                    ),
                    Align(
                      alignment: Alignment.center,
                      child: Text(
                        "167",
                        style: theme.textTheme.displayLarge,
                      ),
                    ),
                  ],
                ),
              ),
              SizedBox(height: 4.v),
              Text(
                "168",
                style: theme.textTheme.displayMedium,
              ),
              Text(
                "169",
                style: theme.textTheme.displaySmall,
              ),
              Text(
                "170",
                style: CustomTextStyles.headlineMediumOpenSansGray800,
              ),
              Spacer(
                flex: 50,
              ),
              _buildButtons(context),
            ],
          ),
        ),
      ),
    );
  }

  /// Section Widget
  Widget _buildButtons(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(left: 15.h),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          CustomIconButton(
            height: 54.adaptSize,
            width: 54.adaptSize,
            padding: EdgeInsets.all(15.h),
            child: CustomImageView(
              imagePath: ImageConstant.imgArrowDown,
            ),
          ),
          CustomElevatedButton(
            width: 168.h,
            text: "Successivo",
            rightIcon: Container(
              margin: EdgeInsets.only(left: 8.h),
              child: CustomImageView(
                imagePath: ImageConstant.imgChevronright,
                height: 24.adaptSize,
                width: 24.adaptSize,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
