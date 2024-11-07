package org.sopt.and.data.dataremote.datasouceimpl

import org.sopt.and.data.dataremote.datasource.UserServiceRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import org.sopt.and.data.dataremote.service.AuthService
import javax.inject.Inject

class UserServiceRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : UserServiceRemoteDataSource {
    override suspend fun registerUserInfo(requestUserInfoRegisterDto: RequestUserInfoRegisterDto): ResponseUserInfoRegisterSuccessDto {
        return authService.registerUserInfo(requestUserInfoRegisterDto)
    }

    override suspend fun userSignIn(requsetSignInDto: RequestSignInDto): ResponseSignInSuccessDto {
        return authService.userSignIn(requsetSignInDto)
    }
}