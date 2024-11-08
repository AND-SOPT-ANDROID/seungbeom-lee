package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.repository.UserServiceRepository
import javax.inject.Inject

class GetUserHobbyUseCase @Inject constructor(
    private val userServiceRepository: UserServiceRepository
) {
    suspend operator fun invoke(token: String): Result<UserHobby> {
        return userServiceRepository.getMyHobby(token)
    }
}