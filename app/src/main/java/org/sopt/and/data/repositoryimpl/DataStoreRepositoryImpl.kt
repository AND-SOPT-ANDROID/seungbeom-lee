package org.sopt.and.data.repositoryimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.sopt.and.domain.repository.DataStoreRepository
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {
    private val tokenKey = stringPreferencesKey("token")

    override suspend fun setToken(token: String) {
        dataStore.edit {
            it[tokenKey] = token
        }
    }


    override suspend fun getToken(): Flow<String> {
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