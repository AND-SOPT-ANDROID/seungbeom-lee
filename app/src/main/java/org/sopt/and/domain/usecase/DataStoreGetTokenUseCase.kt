package org.sopt.and.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.and.domain.repository.DataStoreRepository
import javax.inject.Inject

class DataStoreGetTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return dataStoreRepository.getToken()
    }
}
