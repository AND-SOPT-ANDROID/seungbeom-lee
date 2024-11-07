package org.sopt.and.data.dataremote.repository

import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import org.sopt.and.feature.login.UserToken
import org.sopt.and.feature.signup.SignUpState

interface UserServiceRepository {
    suspend fun registerUserInfo(requestUserInfoRegisterDto: RequestUserInfoRegisterDto
    ): Result<ResponseUserInfoRegisterSuccessDto>

    suspend fun userSignIn(requsetSignInDto: RequestSignInDto
    ): Result<UserToken>
}
