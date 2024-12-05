package org.sopt.and.domain.repository

import kotlinx.coroutines.flow.Flow


interface DataStoreRepository {
    suspend fun setToken(token: String)

    suspend fun getToken(): Flow<String>
}
