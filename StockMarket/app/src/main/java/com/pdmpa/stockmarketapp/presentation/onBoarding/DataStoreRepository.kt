package com.pdmpa.stockmarketapp.presentation.onBoarding

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


object DataStoreConstants {
    const val SETTINGS_DATA_STORE_NAME = "on_boarding_pref"
    const val ON_BOARDING_DID_SHOW_KEY = "on_boarding_completed"
}

class DataStoreRepository(private val dataStore: DataStore<Preferences>) {

    object PreferencesKey {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreConstants.SETTINGS_DATA_STORE_NAME)
        val onBoardingKey =
            booleanPreferencesKey(name = DataStoreConstants.ON_BOARDING_DID_SHOW_KEY)
    }

    suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = isCompleted
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IllegalStateException) {
                    saveOnBoardingState(false)
                    throw IllegalStateException()
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}