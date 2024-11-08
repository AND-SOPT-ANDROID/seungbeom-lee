package org.sopt.and.domain.repository

import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.entity.UserToken

interface UserServiceRepository {
    suspend fun registerUserInfo(requestUserInfoRegisterDto: RequestUserInfoRegisterDto
    ): Result<ResponseUserInfoRegisterSuccessDto>

    suspend fun userSignIn(requsetSignInDto: RequestSignInDto
    ): Result<UserToken>

    suspend fun getMyHobby(token:String
    ) : Result<UserHobby>
}

