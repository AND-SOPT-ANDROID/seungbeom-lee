package org.sopt.and.data.mapper

import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.domain.entity.UserToken

fun ResponseSignInSuccessDto.toDomain() : UserToken = UserToken(
    token = this.token
)