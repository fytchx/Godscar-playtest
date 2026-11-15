package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "godscar_prefs")

class PreferencesRepository(private val context: Context) {
    private val showRecapKey = booleanPreferencesKey("godscar_show_recap")

    val showRecapFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[showRecapKey] ?: true
    }

    suspend fun getShowRecapOnce(): Boolean = showRecapFlow.first()

    suspend fun setShowRecap(show: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[showRecapKey] = show
        }
    }
}
