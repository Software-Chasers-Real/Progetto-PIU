import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';

class GymPlaneScreen extends StatelessWidget {
  const GymPlaneScreen({Key? key})
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
            horizontal: 28.h,
            vertical: 52.v,
          ),
          child: Column(
            children: [
              CustomImageView(
                imagePath: ImageConstant.imgImage100x107,
                height: 100.v,
                width: 107.h,
                radius: BorderRadius.circular(
                  53.h,
                ),
              ),
              SizedBox(height: 47.v),
              Text(
                "Scegli il tuo abbonamento",
                style: CustomTextStyles.bodyLargeAnton18,
              ),
              SizedBox(height: 22.v),
              _buildGymPlaneComponentOne(context),
              SizedBox(height: 5.v),
            ],
          ),
        ),
      ),
    );
  }

  /// Section Widget
  Widget _buildGymPlaneComponentOne(BuildContext context) {
    return Container(
      width: 318.h,
      margin: EdgeInsets.only(left: 1.h),
      padding: EdgeInsets.symmetric(
        horizontal: 9.h,
        vertical: 12.v,
      ),
      decoration: AppDecoration.outlinePrimary.copyWith(
        borderRadius: BorderRadiusStyle.roundedBorder16,
      ),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Align(
            alignment: Alignment.center,
            child: Text(
              "Fitness Starter",
              style: CustomTextStyles.bodyLargeActor,
            ),
          ),
          SizedBox(height: 7.v),
          Align(
            alignment: Alignment.center,
            child: Text(
              " 30€ al mese",
              style: CustomTextStyles.bodyMediumOpenSans13,
            ),
          ),
          SizedBox(height: 14.v),
          Padding(
            padding: EdgeInsets.only(right: 31.h),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(
                  height: 10.adaptSize,
                  width: 10.adaptSize,
                  margin: EdgeInsets.only(
                    top: 2.v,
                    bottom: 14.v,
                  ),
                  decoration: BoxDecoration(
                    color: theme.colorScheme.errorContainer,
                    borderRadius: BorderRadius.circular(
                      5.h,
                    ),
                  ),
                ),
                Expanded(
                  child: Container(
                    width: 251.h,
                    margin: EdgeInsets.only(left: 4.h),
                    child: Text(
                      "Accesso illimitato alla sala pesi e alle attrezzature\ncardio.",
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                      style: theme.textTheme.bodySmall!.copyWith(
                        height: 1.18,
                      ),
                    ),
                  ),
                ),
              ],
            ),
          ),
          SizedBox(height: 2.v),
          Padding(
            padding: EdgeInsets.only(right: 57.h),
            child: Row(
              children: [
                Container(
                  height: 10.adaptSize,
                  width: 10.adaptSize,
                  margin: EdgeInsets.only(
                    top: 1.v,
                    bottom: 3.v,
                  ),
                  decoration: BoxDecoration(
                    color: theme.colorScheme.errorContainer,
                    borderRadius: BorderRadius.circular(
                      5.h,
                    ),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(left: 4.h),
                  child: Text(
                    "Partecipazione a lezioni di gruppo standard.",
                    style: theme.textTheme.bodySmall,
                  ),
                ),
              ],
            ),
          ),
          SizedBox(height: 3.v),
          Padding(
            padding: EdgeInsets.only(right: 67.h),
            child: Row(
              children: [
                Container(
                  height: 10.adaptSize,
                  width: 10.adaptSize,
                  margin: EdgeInsets.only(
                    top: 1.v,
                    bottom: 3.v,
                  ),
                  decoration: BoxDecoration(
                    color: theme.colorScheme.errorContainer,
                    borderRadius: BorderRadius.circular(
                      5.h,
                    ),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(left: 4.h),
                  child: Text(
                    "Assistenza di base da parte del personale.",
                    style: theme.textTheme.bodySmall,
                  ),
                ),
              ],
            ),
          ),
          SizedBox(height: 3.v),
          Row(
            children: [
              Container(
                height: 10.adaptSize,
                width: 10.adaptSize,
                margin: EdgeInsets.only(
                  top: 1.v,
                  bottom: 3.v,
                ),
                decoration: BoxDecoration(
                  color: theme.colorScheme.errorContainer,
                  borderRadius: BorderRadius.circular(
                    5.h,
                  ),
                ),
              ),
              Padding(
                padding: EdgeInsets.only(left: 4.h),
                child: Text(
                  "Accesso ai servizi igienici e spogliatoi.",
                  style: theme.textTheme.bodySmall,
                ),
              ),
            ],
          ),
          SizedBox(height: 28.v),
        ],
      ),
    );
  }
}
