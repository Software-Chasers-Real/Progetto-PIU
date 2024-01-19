import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';

class GenderTwoScreen extends StatelessWidget {
  const GenderTwoScreen({Key? key})
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
            horizontal: 9.h,
            vertical: 44.v,
          ),
          child: Column(
            children: [
              SizedBox(height: 28.v),
              Text(
                "Parlici di te stesso!",
                style: theme.textTheme.headlineMedium,
              ),
              SizedBox(height: 5.v),
              Container(
                width: 301.h,
                margin: EdgeInsets.only(
                  left: 24.h,
                  right: 31.h,
                ),
                child: Text(
                  "Per darvi una migliore esperienza abbiamo bisogno\r\nper conoscere il vostro genere",
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  textAlign: TextAlign.center,
                  style: theme.textTheme.bodyMedium!.copyWith(
                    height: 1.14,
                  ),
                ),
              ),
              Spacer(
                flex: 44,
              ),
              Padding(
                padding: EdgeInsets.symmetric(horizontal: 108.h),
                child: _buildGenderColumn(
                  context,
                  venus: ImageConstant.imgMarsOnprimarycontainer,
                  male: "Uomo",
                ),
              ),
              SizedBox(height: 44.v),
              Padding(
                padding: EdgeInsets.symmetric(horizontal: 108.h),
                child: _buildGenderColumn(
                  context,
                  venus: ImageConstant.imgVenusOnerror,
                  male: "Donna",
                ),
              ),
              Spacer(
                flex: 55,
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
                alignment: Alignment.centerRight,
              ),
            ],
          ),
        ),
      ),
    );
  }

  /// Common widget
  Widget _buildGenderColumn(
    BuildContext context, {
    required String venus,
    required String male,
  }) {
    return Container(
      padding: EdgeInsets.symmetric(
        horizontal: 44.h,
        vertical: 15.v,
      ),
      decoration: AppDecoration.fillPrimary.copyWith(
        borderRadius: BorderRadiusStyle.circleBorder70,
      ),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          SizedBox(height: 20.v),
          CustomImageView(
            imagePath: venus,
            height: 48.adaptSize,
            width: 48.adaptSize,
            alignment: Alignment.center,
          ),
          SizedBox(height: 19.v),
          Text(
            male,
            style: CustomTextStyles.bodyMediumOpenSansOnError.copyWith(
              color: theme.colorScheme.onError,
            ),
          ),
        ],
      ),
    );
  }
}
