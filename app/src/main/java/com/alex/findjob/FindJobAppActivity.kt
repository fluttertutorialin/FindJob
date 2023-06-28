package com.alex.findjob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import com.alex.findjob.localization.Localization
import com.alex.findjob.screens.main.MainScreen
import com.alex.findjob.screens.onboard.OnBoardScreen
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
            val locale by remember {
                mutableStateOf(Locale(Locale.ENGLISH.toString()))
            }
            Localization(locale = locale) {
                // TODO: clear
//                registerSupportedLocales(Locale.ENGLISH)
                AppTheme(darkTheme = false) {
                    ProvideWindowInsets(windowInsetsAnimationsEnabled = true, consumeWindowInsets = true) {
                        Navigator(SplashScreen())
                    }
                }
            }
        }
    }
}
