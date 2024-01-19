import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/app_bar/appbar_circleimage.dart';
import 'package:vero_progetto_piu/widgets/app_bar/appbar_title.dart';
import 'package:vero_progetto_piu/widgets/app_bar/custom_app_bar.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';
import 'package:vero_progetto_piu/widgets/custom_icon_button.dart';
import 'package:vero_progetto_piu/widgets/custom_text_form_field.dart';

class LoginScreen extends StatelessWidget {
  LoginScreen({Key? key})
      : super(
          key: key,
        );

  TextEditingController emailController = TextEditingController();

  TextEditingController passwordController = TextEditingController();

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        resizeToAvoidBottomInset: false,
        body: SizedBox(
          width: SizeUtils.width,
          child: SingleChildScrollView(
            padding: EdgeInsets.only(
              bottom: MediaQuery.of(context).viewInsets.bottom,
            ),
            child: Form(
              key: _formKey,
              child: SizedBox(
                width: double.maxFinite,
                child: Column(
                  children: [
                    Container(
                      padding: EdgeInsets.symmetric(vertical: 44.v),
                      decoration: BoxDecoration(
                        image: DecorationImage(
                          image: AssetImage(
                            ImageConstant.imgBackgroundPrimary,
                          ),
                          fit: BoxFit.cover,
                        ),
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          _buildAppBar(context),
                          SizedBox(height: 190.v),
                          Container(
                            width: 167.h,
                            margin: EdgeInsets.only(left: 32.h),
                            child: Text(
                              "Bentornata,\nMaria",
                              maxLines: 2,
                              overflow: TextOverflow.ellipsis,
                              style: CustomTextStyles
                                  .displaySmallAntonOnPrimaryContainer
                                  .copyWith(
                                height: 1.19,
                              ),
                            ),
                          ),
                          SizedBox(height: 29.v),
                        ],
                      ),
                    ),
                    _buildLoginForm(context),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }

  /// Section Widget
  PreferredSizeWidget _buildAppBar(BuildContext context) {
    return CustomAppBar(
      title: Padding(
        padding: EdgeInsets.only(left: 32.h),
        child: Column(
          children: [
            Row(
              children: [
                AppbarTitle(
                  text: "Accedi",
                  margin: EdgeInsets.only(bottom: 1.v),
                ),
                AppbarTitle(
                  text: "Registrati",
                  margin: EdgeInsets.only(
                    left: 26.h,
                    top: 1.v,
                  ),
                ),
              ],
            ),
            SizedBox(height: 4.v),
            Align(
              alignment: Alignment.centerLeft,
              child: Container(
                height: 3.v,
                width: 42.h,
                margin: EdgeInsets.only(right: 84.h),
                decoration: BoxDecoration(
                  color: theme.colorScheme.primary,
                ),
              ),
            ),
          ],
        ),
      ),
      actions: [
        Container(
          margin: EdgeInsets.symmetric(horizontal: 32.h),
          padding: EdgeInsets.all(3.h),
          decoration: AppDecoration.outlineOnPrimaryContainer.copyWith(
            borderRadius: BorderRadiusStyle.circleBorder28,
          ),
          child: AppbarCircleimage(
            imagePath: ImageConstant.img59,
          ),
        ),
      ],
    );
  }

  /// Section Widget
  Widget _buildLoginForm(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(
        horizontal: 18.h,
        vertical: 44.v,
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          SizedBox(height: 4.v),
          Padding(
            padding: EdgeInsets.symmetric(horizontal: 14.h),
            child: CustomTextFormField(
              controller: emailController,
              hintText: "Email",
              textInputType: TextInputType.emailAddress,
              contentPadding: EdgeInsets.symmetric(horizontal: 16.h),
            ),
          ),
          SizedBox(height: 37.v),
          Padding(
            padding: EdgeInsets.symmetric(horizontal: 14.h),
            child: CustomTextFormField(
              controller: passwordController,
              hintText: "Password",
              textInputAction: TextInputAction.done,
              textInputType: TextInputType.visiblePassword,
              obscureText: true,
              contentPadding: EdgeInsets.symmetric(horizontal: 16.h),
            ),
          ),
          SizedBox(height: 18.v),
          Align(
            alignment: Alignment.centerRight,
            child: Text(
              "Password dimenticata",
              style: CustomTextStyles.labelLargePrimary,
            ),
          ),
          SizedBox(height: 47.v),
          Padding(
            padding: EdgeInsets.only(
              left: 14.h,
              right: 6.h,
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                CustomIconButton(
                  height: 54.adaptSize,
                  width: 54.adaptSize,
                  padding: EdgeInsets.all(15.h),
                  child: CustomImageView(
                    imagePath: ImageConstant.imgApple,
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(left: 21.h),
                  child: CustomIconButton(
                    height: 54.adaptSize,
                    width: 54.adaptSize,
                    padding: EdgeInsets.all(15.h),
                    child: CustomImageView(
                      imagePath: ImageConstant.imgGoogle,
                    ),
                  ),
                ),
                Spacer(),
                CustomElevatedButton(
                  width: 134.h,
                  text: "Accedi",
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
          ),
        ],
      ),
    );
  }
}
