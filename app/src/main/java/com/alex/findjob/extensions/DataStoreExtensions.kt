package com.alex.findjob.extensions

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.firstOrNull

suspend fun <T, P: Preferences.Key<*>> DataStore<Preferences>.get(key: P, defaultValue: T) =
    data.firstOrNull()?.let {
        it[key] as T
    } ?: defaultValue
