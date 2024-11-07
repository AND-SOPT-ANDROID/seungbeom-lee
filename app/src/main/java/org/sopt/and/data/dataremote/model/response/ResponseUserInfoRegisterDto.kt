package org.sopt.and.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseUserInfoRegisterSuccessDto(
    @SerialName("result")
    val result: Result
)  {
    @Serializable
    data class Result(
        @SerialName("no")
        val no: Int
    )
}

@Serializable
data class ResponseUserInfoRegisterFailureDto(
    @SerialName("code")
    val code: String
)