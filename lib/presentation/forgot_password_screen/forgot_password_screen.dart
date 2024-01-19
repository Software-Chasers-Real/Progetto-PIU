import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/app_bar/appbar_leading_iconbutton.dart';
import 'package:vero_progetto_piu/widgets/app_bar/custom_app_bar.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';
import 'package:vero_progetto_piu/widgets/custom_text_form_field.dart';

class ForgotPasswordScreen extends StatelessWidget {
  ForgotPasswordScreen({Key? key})
      : super(
          key: key,
        );

  TextEditingController emailController = TextEditingController();

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: _buildAppBar(context),
        body: SizedBox(
          width: SizeUtils.width,
          child: SingleChildScrollView(
            padding: EdgeInsets.only(
              bottom: MediaQuery.of(context).viewInsets.bottom,
            ),
            child: Form(
              key: _formKey,
              child: Container(
                width: double.maxFinite,
                padding: EdgeInsets.symmetric(
                  horizontal: 32.h,
                  vertical: 6.v,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "Password dimenticata?",
                      style: theme.textTheme.headlineMedium,
                    ),
                    SizedBox(height: 9.v),
                    SizedBox(
                      width: 174.h,
                      child: Text(
                        "Inserisci i tuoi dati qui sotto o\r\naccedi con un altro account",
                        maxLines: 2,
                        overflow: TextOverflow.ellipsis,
                        style: theme.textTheme.bodyMedium!.copyWith(
                          height: 1.14,
                        ),
                      ),
                    ),
                    SizedBox(height: 48.v),
                    CustomTextFormField(
                      controller: emailController,
                      hintText: "Email",
                      textInputAction: TextInputAction.done,
                      textInputType: TextInputType.emailAddress,
                      contentPadding: EdgeInsets.symmetric(horizontal: 16.h),
                    ),
                    Spacer(
                      flex: 25,
                    ),
                    Align(
                      alignment: Alignment.center,
                      child: Text(
                        "Prova con un’altro metodo",
                        style: CustomTextStyles.labelLargePrimary_1,
                      ),
                    ),
                    SizedBox(height: 30.v),
                    CustomElevatedButton(
                      text: "Invia",
                      margin: EdgeInsets.symmetric(horizontal: 24.h),
                      alignment: Alignment.center,
                    ),
                    Spacer(
                      flex: 74,
                    ),
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
      leadingWidth: double.maxFinite,
      leading: AppbarLeadingIconbutton(
        imagePath: ImageConstant.imgArrowLeft,
        margin: EdgeInsets.fromLTRB(24.h, 12.v, 319.h, 12.v),
      ),
    );
  }
}
