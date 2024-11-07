package org.sopt.and.data.dataremote.mapper

import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.feature.login.UserToken

fun ResponseSignInSuccessDto.toDomain() : UserToken = UserToken(
    token = this.result.token
)