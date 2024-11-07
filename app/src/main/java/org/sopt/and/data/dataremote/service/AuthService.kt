package org.sopt.and.data.dataremote.service

import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import retrofit2.http.POST

interface AuthService {
    @POST("/user")
    suspend fun registerUserInfo(
        requestUserInfoRegisterDto: RequestUserInfoRegisterDto
    ): ResponseUserInfoRegisterSuccessDto

    @POST("/login")
    suspend fun userSignIn(
        requsetSignInDto: RequestSignInDto
    ): ResponseSignInSuccessDto
}