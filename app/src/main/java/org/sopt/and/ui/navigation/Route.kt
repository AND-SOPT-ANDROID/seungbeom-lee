package org.sopt.and.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object SignUp

    @Serializable
    data object Home

    @Serializable
    data class LogIn(
        val email: String,
        val password: String
    )

    @Serializable
    data class MyProFile(
        val email: String,
        val password: String
    )

    @Serializable
    data object Search

}