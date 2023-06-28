package com.alex.findjob.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.alex.findjob.data.auth.DataSource
import com.alex.findjob.data.auth.DataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val nameStoreKey = stringPreferencesKey("name_key")
val firstRunKey = booleanPreferencesKey("first_run_key")
val lastSearchResponse = stringPreferencesKey("last_search_response_key")
val lastSearchRequest = stringPreferencesKey("last_search_request_key")

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {
    private const val PREFERENCE_DATA_STORE_NAME = "FIND_JOB_PREFERENCES"
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_DATA_STORE_NAME)

    @Singleton
    @Provides
    fun providesDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

    @Singleton
    @Provides
    fun providesAuthDataSource(dataStore: DataStore<Preferences>): DataSource =
        DataSourceImpl(dataStore)

}
