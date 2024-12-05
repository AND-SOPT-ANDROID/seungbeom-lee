package org.sopt.and.data.mapper

import org.sopt.and.data.dataremote.model.response.ResponseGetMyHobbyDto
import org.sopt.and.domain.entity.UserHobby

fun ResponseGetMyHobbyDto.toDomain() : UserHobby = UserHobby(
    hobby = this.hobby
)