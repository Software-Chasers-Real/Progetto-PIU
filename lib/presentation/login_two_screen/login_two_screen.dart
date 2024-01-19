import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';
import 'package:vero_progetto_piu/widgets/custom_floating_text_field.dart';
import 'package:vero_progetto_piu/widgets/custom_icon_button.dart';

class LoginTwoScreen extends StatelessWidget {
  LoginTwoScreen({Key? key})
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
                      padding: EdgeInsets.symmetric(
                        horizontal: 32.h,
                        vertical: 44.v,
                      ),
                      decoration: BoxDecoration(
                        image: DecorationImage(
                          image: AssetImage(
                            ImageConstant.imgBackgroundPrimary460x375,
                          ),
                          fit: BoxFit.cover,
                        ),
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          _buildLoginRow(context),
                          SizedBox(height: 190.v),
                          SizedBox(
                            width: 167.h,
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
                    SizedBox(height: 31.v),
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 32.h),
                      child: CustomFloatingTextField(
                        controller: emailController,
                        labelText: "Email",
                        labelStyle:
                            CustomTextStyles.titleMediumOnPrimaryContainer,
                        hintText: "Email",
                        textInputType: TextInputType.emailAddress,
                        suffix: Container(
                          margin: EdgeInsets.symmetric(horizontal: 13.h),
                          child: CustomImageView(
                            imagePath: ImageConstant.imgTickSquare,
                            height: 24.adaptSize,
                            width: 24.adaptSize,
                          ),
                        ),
                        suffixConstraints: BoxConstraints(
                          maxHeight: 59.v,
                        ),
                      ),
                    ),
                    SizedBox(height: 20.v),
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 32.h),
                      child: CustomFloatingTextField(
                        controller: passwordController,
                        labelText: "Password",
                        labelStyle:
                            CustomTextStyles.titleMediumOnPrimaryContainer,
                        hintText: "Password",
                        textInputAction: TextInputAction.done,
                        textInputType: TextInputType.visiblePassword,
                        obscureText: true,
                        suffix: Container(
                          margin: EdgeInsets.symmetric(horizontal: 13.h),
                          child: CustomImageView(
                            imagePath: ImageConstant.imgEye,
                            height: 24.adaptSize,
                            width: 24.adaptSize,
                          ),
                        ),
                        suffixConstraints: BoxConstraints(
                          maxHeight: 59.v,
                        ),
                      ),
                    ),
                    SizedBox(height: 18.v),
                    Align(
                      alignment: Alignment.centerRight,
                      child: Padding(
                        padding: EdgeInsets.only(right: 8.h),
                        child: Text(
                          "Password dimenticata",
                          style: CustomTextStyles.labelLargePrimary_1,
                        ),
                      ),
                    ),
                    SizedBox(height: 46.v),
                    _buildLoginButtons(context),
                    SizedBox(height: 5.v),
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
  Widget _buildLoginRow(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: EdgeInsets.only(
            top: 13.v,
            bottom: 16.v,
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              SizedBox(
                width: 126.h,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Padding(
                      padding: EdgeInsets.only(bottom: 1.v),
                      child: Text(
                        "Accedi",
                        style: theme.textTheme.labelLarge,
                      ),
                    ),
                    Text(
                      "Registrati",
                      style: theme.textTheme.labelLarge,
                    ),
                  ],
                ),
              ),
              SizedBox(height: 4.v),
              Container(
                height: 3.v,
                width: 42.h,
                decoration: BoxDecoration(
                  color: theme.colorScheme.primary,
                ),
              ),
            ],
          ),
        ),
        Container(
          height: 56.adaptSize,
          width: 56.adaptSize,
          padding: EdgeInsets.all(3.h),
          decoration: AppDecoration.outlineOnPrimaryContainer.copyWith(
            borderRadius: BorderRadiusStyle.circleBorder28,
          ),
          child: CustomImageView(
            imagePath: ImageConstant.img59,
            height: 50.adaptSize,
            width: 50.adaptSize,
            radius: BorderRadius.circular(
              25.h,
            ),
            alignment: Alignment.center,
          ),
        ),
      ],
    );
  }

  /// Section Widget
  Widget _buildLoginButtons(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(
        left: 32.h,
        right: 24.h,
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
    );
  }
}
