import 'package:flutter/material.dart';
import 'package:vero_progetto_piu/core/app_export.dart';

/// A collection of pre-defined text styles for customizing text appearance,
/// categorized by different font families and weights.
/// Additionally, this class includes extensions on [TextStyle] to easily apply specific font families to text.

class CustomTextStyles {
  // Body text style
  static get bodyLargeActor => theme.textTheme.bodyLarge!.actor.copyWith(
        fontSize: 19.fSize,
      );
  static get bodyLargeAnton => theme.textTheme.bodyLarge!.anton.copyWith(
        fontSize: 19.fSize,
      );
  static get bodyLargeAnton18 => theme.textTheme.bodyLarge!.anton.copyWith(
        fontSize: 18.fSize,
      );
  static get bodyMediumOpenSans =>
      theme.textTheme.bodyMedium!.openSans.copyWith(
        fontSize: 15.fSize,
      );
  static get bodyMediumOpenSans13 =>
      theme.textTheme.bodyMedium!.openSans.copyWith(
        fontSize: 13.fSize,
      );
  static get bodyMediumOpenSansOnError =>
      theme.textTheme.bodyMedium!.openSans.copyWith(
        color: theme.colorScheme.onError,
        fontSize: 15.fSize,
      );
  static get bodySmallPrimary => theme.textTheme.bodySmall!.copyWith(
        color: theme.colorScheme.primary,
      );
  static get bodySmallRedA400 => theme.textTheme.bodySmall!.copyWith(
        color: appTheme.redA400,
      );
  // Display text style
  static get displayLarge64 => theme.textTheme.displayLarge!.copyWith(
        fontSize: 64.fSize,
      );
  static get displayMediumNicoMojiPrimary =>
      theme.textTheme.displayMedium!.nicoMoji.copyWith(
        color: theme.colorScheme.primary,
        fontSize: 50.fSize,
      );
  static get displaySmallAntonOnPrimaryContainer =>
      theme.textTheme.displaySmall!.anton.copyWith(
        color: theme.colorScheme.onPrimaryContainer,
        fontSize: 36.fSize,
      );
  // Headline text style
  static get headlineMediumOpenSans =>
      theme.textTheme.headlineMedium!.openSans.copyWith(
        fontWeight: FontWeight.w600,
      );
  static get headlineMediumOpenSansGray800 =>
      theme.textTheme.headlineMedium!.openSans.copyWith(
        color: appTheme.gray800,
        fontSize: 27.fSize,
      );
  // Label text style
  static get labelLargePrimary => theme.textTheme.labelLarge!.copyWith(
        color: theme.colorScheme.primary,
        fontSize: 12.fSize,
      );
  static get labelLargePrimary_1 => theme.textTheme.labelLarge!.copyWith(
        color: theme.colorScheme.primary,
      );
  // Title text style
  static get titleLargeAntonOnPrimaryContainer =>
      theme.textTheme.titleLarge!.anton.copyWith(
        color: theme.colorScheme.onPrimaryContainer,
        fontSize: 21.fSize,
      );
  static get titleMediumOnPrimaryContainer =>
      theme.textTheme.titleMedium!.copyWith(
        color: theme.colorScheme.onPrimaryContainer,
      );
}

extension on TextStyle {
  TextStyle get nicoMoji {
    return copyWith(
      fontFamily: 'Nico Moji',
    );
  }

  TextStyle get anton {
    return copyWith(
      fontFamily: 'Anton',
    );
  }

  TextStyle get openSans {
    return copyWith(
      fontFamily: 'Open Sans',
    );
  }

  TextStyle get actor {
    return copyWith(
      fontFamily: 'Actor',
    );
  }
}
