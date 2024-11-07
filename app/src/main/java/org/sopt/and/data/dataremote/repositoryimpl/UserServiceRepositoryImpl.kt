package org.sopt.and.data.dataremote.repositoryimpl

import org.sopt.and.data.dataremote.datasouceimpl.UserServiceRemoteDataSourceImpl
import org.sopt.and.data.dataremote.datasource.UserServiceRemoteDataSource
import org.sopt.and.data.dataremote.mapper.toDomain
import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import org.sopt.and.data.dataremote.repository.UserServiceRepository
import org.sopt.and.feature.login.UserToken
import javax.inject.Inject

class UserServiceRepositoryImpl @Inject constructor(
    private val userServiceRemoteDataSource: UserServiceRemoteDataSource
) : UserServiceRepository {
    override suspend fun registerUserInfo(requestUserInfoRegisterDto: RequestUserInfoRegisterDto): Result<ResponseUserInfoRegisterSuccessDto> =
        runCatching {
            userServiceRemoteDataSource.registerUserInfo(requestUserInfoRegisterDto)
        }

    override suspend fun userSignIn(requsetSignInDto: RequestSignInDto): Result<UserToken> =
        runCatching {
            userServiceRemoteDataSource.userSignIn(requsetSignInDto).toDomain()
        }

}