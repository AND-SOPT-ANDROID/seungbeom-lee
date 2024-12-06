package org.sopt.and.data.service

import org.sopt.and.data.dataremote.model.BaseResponse
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
    ): BaseResponse<ResponseUserInfoRegisterSuccessDto>

    @POST("/login")
    suspend fun userSignIn(
        @Body requsetSignInDto: RequestSignInDto
    ): BaseResponse<ResponseSignInSuccessDto>

    @GET("user/my-hobby")
    suspend fun getMyHobby(
        @Header("token") token: String
    ): BaseResponse<ResponseGetMyHobbyDto>
}