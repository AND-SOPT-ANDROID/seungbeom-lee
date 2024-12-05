package org.sopt.and.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.and.domain.repository.DataStoreRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return dataStoreRepository.getToken()
    }
}
