package org.sopt.and.data.dataremote.datasource

import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto

interface UserServiceRemoteDataSource {
    suspend fun registerUserInfo(requestUserInfoRegisterDto: RequestUserInfoRegisterDto
    ): ResponseUserInfoRegisterSuccessDto

    suspend fun userSignIn(requsetSignInDto: RequestSignInDto
    ): ResponseSignInSuccessDto
}