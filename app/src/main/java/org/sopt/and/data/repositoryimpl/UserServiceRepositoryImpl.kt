package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.dataremote.datasource.UserServiceRemoteDataSource
import org.sopt.and.data.mapper.toDomain
import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.repository.UserServiceRepository
import org.sopt.and.domain.entity.UserToken
import javax.inject.Inject

class UserServiceRepositoryImpl @Inject constructor(
    private val userServiceRemoteDataSource: UserServiceRemoteDataSource
) : UserServiceRepository {
    override suspend fun registerUserInfo(requestUserInfoRegisterDto: RequestUserInfoRegisterDto): Result<ResponseUserInfoRegisterSuccessDto> =
        runCatching {
            userServiceRemoteDataSource.registerUserInfo(requestUserInfoRegisterDto).result
        }

    override suspend fun userSignIn(requsetSignInDto: RequestSignInDto): Result<UserToken> =
        runCatching {
            userServiceRemoteDataSource.userSignIn(requsetSignInDto).result.toDomain()
        }

    override suspend fun getMyHobby(token: String): Result<UserHobby> =
        runCatching {
            userServiceRemoteDataSource.getMyHobby(token).result.toDomain()
        }
}