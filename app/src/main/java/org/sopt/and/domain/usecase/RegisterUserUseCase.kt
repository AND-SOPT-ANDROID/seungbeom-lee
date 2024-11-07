package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import org.sopt.and.domain.repository.UserServiceRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userServiceRepository: UserServiceRepository
) {
    suspend operator fun invoke(request: RequestUserInfoRegisterDto): Result<ResponseUserInfoRegisterSuccessDto> {
        return userServiceRepository.registerUserInfo(request)
    }
}