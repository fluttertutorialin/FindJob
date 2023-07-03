package com.alex.findjob.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)
private val MainBlueColor = Color(0xFF1C58F2)
private val Onboard1Background = Color(0xFFD0BCFF)
private val Onboard2Background = Color(0xFFA7DDFF)
private val Onboard3Background = Color(0xFFFFECB2)
private val ButtonOnboard1Text = Color(0xFF8A5EF0)
private val ButtonOnboard2Text = Color(0xFF1F7CB5)
private val ButtonOnboard3Text = Color(0xFF000000)
private val GreyTextColor = Color(0xFF6B6B6B)
private val DividerColor = Color(0x80EDEDED)
private val LightGreyColor = Color(0xFFF5F7FC)

@Immutable
data class AppSpecificColors(
    val onboard1Background: Color,
    val onboard2Background: Color,
    val onboard3Background: Color,
    val buttonOnboard1Text: Color,
    val buttonOnboard2Text: Color,
    val buttonOnboard3Text: Color,
    val white: Color,
    val black: Color,
    val greyTextColor: Color,
    val dividerColor: Color,
    val lightGreyColor: Color,
    val mainBlueColor: Color
)

private val LocalAppSpecificColors = staticCompositionLocalOf {
    AppSpecificColors(
        onboard1Background = Onboard1Background,
        onboard2Background = Onboard2Background,
        onboard3Background = Onboard3Background,
        buttonOnboard1Text = ButtonOnboard1Text,
        buttonOnboard2Text = ButtonOnboard2Text,
        buttonOnboard3Text = ButtonOnboard3Text,
        white = White,
        black = Black,
        greyTextColor = GreyTextColor,
        dividerColor = DividerColor,
        lightGreyColor = LightGreyColor,
        mainBlueColor = MainBlueColor
    )
}

private val darkAppSpecificColors = AppSpecificColors(
    onboard1Background = Onboard1Background,
    onboard2Background = Onboard2Background,
    onboard3Background = Onboard3Background,
    buttonOnboard1Text = ButtonOnboard1Text,
    buttonOnboard2Text = ButtonOnboard2Text,
    buttonOnboard3Text = ButtonOnboard3Text,
    white = White,
    black = Black,
    greyTextColor = GreyTextColor,
    dividerColor = DividerColor,
    lightGreyColor = LightGreyColor,
    mainBlueColor = MainBlueColor
)

private val lightAppSpecificColors = AppSpecificColors(
    onboard1Background = Onboard1Background,
    onboard2Background = Onboard2Background,
    onboard3Background = Onboard3Background,
    buttonOnboard1Text = ButtonOnboard1Text,
    buttonOnboard2Text = ButtonOnboard2Text,
    buttonOnboard3Text = ButtonOnboard3Text,
    white = White,
    black = Black,
    greyTextColor = GreyTextColor,
    dividerColor = DividerColor,
    lightGreyColor = LightGreyColor,
    mainBlueColor = MainBlueColor
)

object AppColors {
    val Onboard1Background: Color @Composable get() = AppTheme.colors.onboard1Background
    val Onboard2Background: Color @Composable get() = AppTheme.colors.onboard2Background
    val Onboard3Background: Color @Composable get() = AppTheme.colors.onboard3Background
    val ButtonOnboard1Text: Color @Composable get() = AppTheme.colors.buttonOnboard1Text
    val ButtonOnboard2Text: Color @Composable get() = AppTheme.colors.buttonOnboard2Text
    val ButtonOnboard3Text: Color @Composable get() = AppTheme.colors.buttonOnboard3Text
    val White: Color @Composable get() = AppTheme.colors.white
    val Black: Color @Composable get() = AppTheme.colors.black
    val MainBlueColor: Color @Composable get() = AppTheme.colors.mainBlueColor
    val GreyTextColor: Color @Composable get() = AppTheme.colors.greyTextColor
    val DividerColor: Color @Composable get() = AppTheme.colors.dividerColor
    val LightGreyColor: Color @Composable get() = AppTheme.colors.lightGreyColor
}

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val brixbyColors = if (darkTheme) {
        darkAppSpecificColors
    } else {
        lightAppSpecificColors
    }

    CompositionLocalProvider(LocalAppSpecificColors provides brixbyColors) {
        MaterialTheme(
            content = content
        )
    }
}

object AppTheme {
    val colors: AppSpecificColors
        @Composable
        get() = LocalAppSpecificColors.current
}
