package com.alex.findjob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import com.alex.findjob.extensions.checkEnLocale
import com.alex.findjob.localization.Localization
import com.alex.findjob.localization.registerSupportedLocales
import com.alex.findjob.screens.splash.SplashScreen
import com.alex.findjob.ui.theme.AppTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class FindJobAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            val currentLocale = Locale.getDefault().checkEnLocale()
            val locale by remember { mutableStateOf(currentLocale) }
            registerSupportedLocales(currentLocale)
            Localization(locale = locale) {
                AppTheme(darkTheme = false) {
                    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                        Navigator(SplashScreen())
                    }
                }
            }
        }
    }

}
