package org.sopt.and.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object SignUp

    @Serializable
    data class LogIn(
        val email: String,
        val password: String
    )
}