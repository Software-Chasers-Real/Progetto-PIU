import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/custom_rating_bar.dart';

class GymListScreen extends StatelessWidget {
  const GymListScreen({Key? key})
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
            vertical: 55.v,
          ),
          child: Column(
            children: [
              Align(
                alignment: Alignment.centerLeft,
                child: Padding(
                  padding: EdgeInsets.only(left: 15.h),
                  child: Text(
                    "Benvenuta Maria",
                    style: theme.textTheme.headlineLarge,
                  ),
                ),
              ),
              SizedBox(height: 25.v),
              Text(
                "Seleziona una palestra tra quelle disponibili nella tua zona",
                style: theme.textTheme.bodyMedium,
              ),
              SizedBox(height: 27.v),
              _buildTrainerCardOne(context),
              SizedBox(height: 13.v),
              _buildTrainerCardTwo(context),
              SizedBox(height: 13.v),
              _buildTrainerCardThree(context),
              SizedBox(height: 15.v),
              _buildTrainerCardFour(context),
              SizedBox(height: 5.v),
            ],
          ),
        ),
      ),
    );
  }

  /// Section Widget
  Widget _buildTrainerCardOne(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 15.h),
      padding: EdgeInsets.symmetric(
        horizontal: 9.h,
        vertical: 16.v,
      ),
      decoration: AppDecoration.fillOnPrimary.copyWith(
        borderRadius: BorderRadiusStyle.roundedBorder16,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        mainAxisSize: MainAxisSize.min,
        children: [
          CustomImageView(
            imagePath: ImageConstant.imgImage,
            height: 64.adaptSize,
            width: 64.adaptSize,
            radius: BorderRadius.circular(
              32.h,
            ),
            margin: EdgeInsets.only(left: 7.h),
          ),
          Padding(
            padding: EdgeInsets.only(
              left: 16.h,
              bottom: 3.v,
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "SGym 875",
                  style: CustomTextStyles.titleMediumOnPrimaryContainer,
                ),
                Text(
                  "Corso Benedetto Croce, 235, Bari",
                  style: theme.textTheme.bodySmall,
                ),
                SizedBox(height: 5.v),
                Row(
                  children: [
                    Text(
                      "4",
                      style: theme.textTheme.bodySmall,
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 10.h),
                      child: CustomRatingBar(
                        initialRating: 0,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          CustomImageView(
            imagePath: ImageConstant.imgArrowRight,
            height: 24.adaptSize,
            width: 24.adaptSize,
            margin: EdgeInsets.only(
              left: 28.h,
              top: 22.v,
              bottom: 18.v,
            ),
          ),
        ],
      ),
    );
  }

  /// Section Widget
  Widget _buildTrainerCardTwo(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(
        left: 13.h,
        right: 17.h,
      ),
      padding: EdgeInsets.symmetric(
        horizontal: 9.h,
        vertical: 16.v,
      ),
      decoration: AppDecoration.fillOnPrimary.copyWith(
        borderRadius: BorderRadiusStyle.roundedBorder16,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        mainAxisSize: MainAxisSize.min,
        children: [
          CustomImageView(
            imagePath: ImageConstant.imgImage64x64,
            height: 64.adaptSize,
            width: 64.adaptSize,
            radius: BorderRadius.circular(
              32.h,
            ),
            margin: EdgeInsets.only(left: 7.h),
          ),
          Padding(
            padding: EdgeInsets.only(
              left: 16.h,
              bottom: 3.v,
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "Physiodrome",
                  style: CustomTextStyles.titleMediumOnPrimaryContainer,
                ),
                Text(
                  "Piazzale Vittorio Locchi poeta, 1, Bari",
                  style: theme.textTheme.bodySmall,
                ),
                SizedBox(height: 4.v),
                Row(
                  children: [
                    Text(
                      "3",
                      style: theme.textTheme.bodySmall,
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 10.h),
                      child: CustomRatingBar(
                        initialRating: 0,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          CustomImageView(
            imagePath: ImageConstant.imgArrowRight,
            height: 24.adaptSize,
            width: 24.adaptSize,
            margin: EdgeInsets.only(
              left: 10.h,
              top: 22.v,
              bottom: 18.v,
            ),
          ),
        ],
      ),
    );
  }

  /// Section Widget
  Widget _buildTrainerCardThree(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(
        left: 13.h,
        right: 17.h,
      ),
      padding: EdgeInsets.symmetric(
        horizontal: 9.h,
        vertical: 15.v,
      ),
      decoration: AppDecoration.fillOnPrimary.copyWith(
        borderRadius: BorderRadiusStyle.roundedBorder16,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisSize: MainAxisSize.min,
        children: [
          CustomImageView(
            imagePath: ImageConstant.imgImage1,
            height: 64.adaptSize,
            width: 64.adaptSize,
            radius: BorderRadius.circular(
              32.h,
            ),
            margin: EdgeInsets.only(left: 7.h),
          ),
          Padding(
            padding: EdgeInsets.only(
              left: 16.h,
              bottom: 3.v,
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "FitActive",
                  style: CustomTextStyles.titleMediumOnPrimaryContainer,
                ),
                SizedBox(height: 1.v),
                Text(
                  "Viale Louis Pasteur, 6, Bari",
                  style: theme.textTheme.bodySmall,
                ),
                SizedBox(height: 5.v),
                Row(
                  children: [
                    Text(
                      "5",
                      style: theme.textTheme.bodySmall,
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 10.h),
                      child: CustomRatingBar(
                        initialRating: 5,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          Spacer(),
          CustomImageView(
            imagePath: ImageConstant.imgArrowRight,
            height: 24.adaptSize,
            width: 24.adaptSize,
            margin: EdgeInsets.only(
              top: 22.v,
              bottom: 18.v,
            ),
          ),
        ],
      ),
    );
  }

  /// Section Widget
  Widget _buildTrainerCardFour(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 15.h),
      padding: EdgeInsets.symmetric(
        horizontal: 9.h,
        vertical: 15.v,
      ),
      decoration: AppDecoration.fillOnPrimary.copyWith(
        borderRadius: BorderRadiusStyle.roundedBorder16,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisSize: MainAxisSize.min,
        children: [
          CustomImageView(
            imagePath: ImageConstant.imgImage2,
            height: 64.adaptSize,
            width: 64.adaptSize,
            radius: BorderRadius.circular(
              32.h,
            ),
            margin: EdgeInsets.only(left: 7.h),
          ),
          Padding(
            padding: EdgeInsets.only(
              left: 16.h,
              bottom: 3.v,
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "Metaclub",
                  style: CustomTextStyles.titleMediumOnPrimaryContainer,
                ),
                SizedBox(height: 2.v),
                Text(
                  "Via Giuseppe Fanelli, 206/16/A5, Bari",
                  style: theme.textTheme.bodySmall,
                ),
                SizedBox(height: 4.v),
                Row(
                  children: [
                    Text(
                      "4",
                      style: theme.textTheme.bodySmall,
                    ),
                    Padding(
                      padding: EdgeInsets.only(left: 10.h),
                      child: CustomRatingBar(
                        initialRating: 0,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          CustomImageView(
            imagePath: ImageConstant.imgArrowRight,
            height: 24.adaptSize,
            width: 24.adaptSize,
            margin: EdgeInsets.only(
              left: 10.h,
              top: 22.v,
              bottom: 18.v,
            ),
          ),
        ],
      ),
    );
  }
}
