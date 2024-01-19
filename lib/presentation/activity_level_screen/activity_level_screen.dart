import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';
import 'package:vero_progetto_piu/widgets/custom_icon_button.dart';

class ActivityLevelScreen extends StatelessWidget {
  const ActivityLevelScreen({Key? key})
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
            horizontal: 20.h,
            vertical: 44.v,
          ),
          child: Column(
            children: [
              SizedBox(height: 27.v),
              SizedBox(
                width: 207.h,
                child: Text(
                  "Qual’è il tuo livello\ndi attività fisica?",
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  textAlign: TextAlign.center,
                  style: theme.textTheme.headlineMedium!.copyWith(
                    height: 1.07,
                  ),
                ),
              ),
              SizedBox(height: 6.v),
              Text(
                "Questo aiuta i coach a creare il tuo piano personalizzato\r",
                style: theme.textTheme.bodyMedium,
              ),
              Spacer(
                flex: 43,
              ),
              Text(
                "Novellino",
                style: theme.textTheme.titleLarge,
              ),
              SizedBox(height: 10.v),
              Text(
                "Principiante",
                style: theme.textTheme.headlineSmall,
              ),
              SizedBox(height: 11.v),
              Divider(
                indent: 44.h,
                endIndent: 44.h,
              ),
              SizedBox(height: 22.v),
              Text(
                "Intermedio",
                style: CustomTextStyles.headlineMediumOpenSans,
              ),
              SizedBox(height: 17.v),
              Divider(
                indent: 44.h,
                endIndent: 44.h,
              ),
              SizedBox(height: 16.v),
              Text(
                "Avanzato",
                style: theme.textTheme.headlineSmall,
              ),
              SizedBox(height: 7.v),
              Text(
                "Vera Baestia",
                style: theme.textTheme.titleLarge,
              ),
              Spacer(
                flex: 56,
              ),
              _buildActivityLevelButtons(context),
            ],
          ),
        ),
      ),
    );
  }

  /// Section Widget
  Widget _buildActivityLevelButtons(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(
        left: 12.h,
        right: 6.h,
      ),
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
            width: 119.h,
            text: "Iniza",
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
