import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';
import 'package:vero_progetto_piu/widgets/app_bar/appbar_leading_iconbutton.dart';
import 'package:vero_progetto_piu/widgets/app_bar/custom_app_bar.dart';
import 'package:vero_progetto_piu/widgets/custom_elevated_button.dart';
import 'package:vero_progetto_piu/widgets/custom_pin_code_text_field.dart';

class VerificationScreen extends StatelessWidget {
  const VerificationScreen({Key? key})
      : super(
          key: key,
        );

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: _buildAppBar(context),
        body: Container(
          width: double.maxFinite,
          padding: EdgeInsets.symmetric(
            horizontal: 32.h,
            vertical: 6.v,
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Verifica",
                style: theme.textTheme.headlineMedium,
              ),
              SizedBox(height: 9.v),
              SizedBox(
                width: 198.h,
                child: Text(
                  "Controlla la tua email. Ti abbiamo inviato il PIN.",
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  style: theme.textTheme.bodyMedium!.copyWith(
                    height: 1.14,
                  ),
                ),
              ),
              SizedBox(height: 40.v),
              CustomPinCodeTextField(
                context: context,
                onChanged: (value) {},
              ),
              Spacer(
                flex: 25,
              ),
              Align(
                alignment: Alignment.center,
                child: Text(
                  "Non hai ricevuto nessun codice?",
                  style: CustomTextStyles.labelLargePrimary_1,
                ),
              ),
              SizedBox(height: 30.v),
              CustomElevatedButton(
                text: "Verifica",
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
