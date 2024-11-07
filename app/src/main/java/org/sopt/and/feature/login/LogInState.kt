package org.sopt.and.feature.login

data class LogInState(
    val username : String = "",
    val password : String = "",
)

data class UserToken(
   val token : String = ""
)
