package com.alex.findjob.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> Flow<T>.collectOnLaunch(key1: Any? = true, block: suspend (T) -> Unit) {
    LaunchedEffect(key1) {
        collect {
            block(it)
        }
    }
}


