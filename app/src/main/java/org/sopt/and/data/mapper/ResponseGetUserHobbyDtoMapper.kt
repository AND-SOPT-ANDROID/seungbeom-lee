package org.sopt.and.data.mapper

import org.sopt.and.data.dataremote.model.response.ResponseGetMyHobbyDto
import org.sopt.and.data.dataremote.model.response.ResponseSignInSuccessDto
import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.entity.UserToken

fun ResponseGetMyHobbyDto.toDomain() : UserHobby = UserHobby(
    hobby = this.result.hobby
)