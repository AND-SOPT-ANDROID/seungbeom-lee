package org.sopt.and.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMyHobbyDto(
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
        @SerialName("hobby")
        val hobby: String
    )
}
