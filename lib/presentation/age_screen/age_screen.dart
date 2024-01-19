import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';
import 'package:vero_progetto_piu/widgets/custom_icon_button.dart';

class AgeScreen extends StatelessWidget {
  const AgeScreen({Key? key})
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
                "Quanti anni hai?",
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
              Text(
                "33",
                style: CustomTextStyles.headlineMediumOpenSansGray800,
              ),
              Text(
                "34",
                style: theme.textTheme.displaySmall,
              ),
              Text(
                "35",
                style: theme.textTheme.displayMedium,
              ),
              SizedBox(height: 2.v),
              SizedBox(
                width: 113.h,
                child: Divider(),
              ),
              SizedBox(height: 1.v),
              Text(
                "36",
                style: theme.textTheme.displayLarge,
              ),
              SizedBox(
                width: 113.h,
                child: Divider(),
              ),
              SizedBox(height: 3.v),
              Text(
                "37",
                style: theme.textTheme.displayMedium,
              ),
              Text(
                "38",
                style: theme.textTheme.displaySmall,
              ),
              Text(
                "39",
                style: CustomTextStyles.headlineMediumOpenSansGray800,
              ),
              Spacer(
                flex: 50,
              ),
              _buildButtonSection(context),
            ],
          ),
        ),
      ),
    );
  }

  /// Section Widget
  Widget _buildButtonSection(BuildContext context) {
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
