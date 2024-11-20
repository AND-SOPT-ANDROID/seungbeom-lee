package org.sopt.and.domain.usecase

import org.sopt.and.domain.repository.DataStoreRepository
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(token: String) {
        return dataStoreRepository.setToken(token)
    }
}
