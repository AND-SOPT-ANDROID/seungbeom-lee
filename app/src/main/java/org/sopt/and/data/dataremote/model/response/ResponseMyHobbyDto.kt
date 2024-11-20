package org.sopt.and.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMyHobbyDto(
    @SerialName("hobby")
    val hobby: String
)

