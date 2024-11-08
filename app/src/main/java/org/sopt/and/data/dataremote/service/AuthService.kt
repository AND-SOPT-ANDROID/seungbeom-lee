package org.sopt.and.data.dataremote.service

import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseGetMyHobbyDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/user")
    suspend fun registerUserInfo(
        @Body requestUserInfoRegisterDto: RequestUserInfoRegisterDto
    ): ResponseUserInfoRegisterSuccessDto

    @POST("/login")
    suspend fun userSignIn(
        @Body requsetSignInDto: RequestSignInDto
    ): ResponseSignInSuccessDto

    @GET("user/my-hobby")
    suspend fun getMyHobby(
        @Header("token") token: String
    ) : ResponseGetMyHobbyDto
}