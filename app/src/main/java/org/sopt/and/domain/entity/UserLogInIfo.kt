package org.sopt.and.domain.entity

data class UserLogInIfo(
    val username : String = "",
    val password : String = "",
)

data class UserToken(
   val token : String = ""
)
