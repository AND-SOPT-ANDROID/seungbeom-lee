package org.sopt.and.data.datalocal.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences> // DataStore<Preferences> 직접 주입
) {
    private val tokenKey = stringPreferencesKey("token")

    suspend fun setToken(token: String) {
        dataStore.edit {
            it[tokenKey] = token
        }
    }


    suspend fun getToken(): Flow<String> {
        return dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[tokenKey] ?: "tokenNull"
        }
    }
}