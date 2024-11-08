package org.sopt.and.domain.entity

data class UserLogInInfo(
    val userName : String = "",
    val password : String = "",
)

data class UserToken(
   val token : String = ""
)

