package org.sopt.and.data.dataremote.datasouceimpl

import org.sopt.and.data.dataremote.datasource.UserServiceRemoteDataSource
import org.sopt.and.data.dataremote.factory.ServicePool
import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto

class UserServiceRemoteDataSourceImpl() : UserServiceRemoteDataSource {
    override suspend fun registerUserInfo(requestUserInfoRegisterDto: RequestUserInfoRegisterDto): ResponseUserInfoRegisterSuccessDto {
        return ServicePool.userService.registerUserInfo(requestUserInfoRegisterDto)
    }

    override suspend fun userSignIn(requsetSignInDto: RequestSignInDto): ResponseSignInSuccessDto {
        return ServicePool.userService.userSignIn(requsetSignInDto)
    }
}