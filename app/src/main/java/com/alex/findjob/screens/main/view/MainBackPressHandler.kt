package com.alex.findjob.screens.main.view

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.alex.findjob.screens.main.MainScreenState

@Composable
fun MainBackPressHandler(
    state: MainScreenState,
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    if(state.showFilter){
        val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)
        val backCallback = remember {
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    currentOnBackPressed()
                }
            }
        }
        DisposableEffect(key1 = backPressedDispatcher) {
            backPressedDispatcher?.addCallback(backCallback)

            onDispose {
                backCallback.remove()
            }
        }
    }
}
