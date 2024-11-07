package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.domain.entity.UserToken
import org.sopt.and.domain.repository.UserServiceRepository
import javax.inject.Inject

class SignInUserUseCase @Inject constructor(
    private val userServiceRepository: UserServiceRepository
) {
    suspend operator fun invoke(request: RequestSignInDto): Result<UserToken> {
        return userServiceRepository.userSignIn(request)
    }
}