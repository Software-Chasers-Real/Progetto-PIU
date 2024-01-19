import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';
import 'package:vero_progetto_piu/widgets/custom_icon_button.dart';

class GoalScreen extends StatelessWidget {
  const GoalScreen({Key? key})
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
                "Qual’è il tuo obiettivo?",
                style: theme.textTheme.headlineMedium,
              ),
              SizedBox(height: 6.v),
              Text(
                "Questo aiuta i coach a creare il tuo piano personalizzato\r",
                style: theme.textTheme.bodyMedium,
              ),
              Spacer(
                flex: 49,
              ),
              Text(
                "Forza",
                style: theme.textTheme.titleLarge,
              ),
              SizedBox(height: 7.v),
              Text(
                "Massa muscolare",
                style: theme.textTheme.headlineSmall,
              ),
              SizedBox(height: 14.v),
              Divider(
                indent: 47.h,
                endIndent: 47.h,
              ),
              SizedBox(height: 20.v),
              Text(
                "Peso",
                style: CustomTextStyles.headlineMediumOpenSans,
              ),
              SizedBox(height: 18.v),
              Divider(
                indent: 47.h,
                endIndent: 47.h,
              ),
              SizedBox(height: 15.v),
              Text(
                "Flessibilità",
                style: theme.textTheme.headlineSmall,
              ),
              SizedBox(height: 8.v),
              Text(
                "Resistenza",
                style: theme.textTheme.titleLarge,
              ),
              Spacer(
                flex: 50,
              ),
              _buildButtonsSection(context),
            ],
          ),
        ),
      ),
    );
  }

  /// Section Widget
  Widget _buildButtonsSection(BuildContext context) {
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
