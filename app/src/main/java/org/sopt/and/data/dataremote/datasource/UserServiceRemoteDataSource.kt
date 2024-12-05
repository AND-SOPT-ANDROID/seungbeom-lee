package org.sopt.and.data.dataremote.datasource

import org.sopt.and.data.dataremote.model.BaseResponse
import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseGetMyHobbyDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import org.sopt.and.domain.entity.UserToken

interface UserServiceRemoteDataSource {
    suspend fun registerUserInfo(requestUserInfoRegisterDto: RequestUserInfoRegisterDto
    ): BaseResponse<ResponseUserInfoRegisterSuccessDto>

    suspend fun userSignIn(requsetSignInDto: RequestSignInDto
    ): BaseResponse<ResponseSignInSuccessDto>

    suspend fun getMyHobby(token : String
    ) : BaseResponse<ResponseGetMyHobbyDto>
}