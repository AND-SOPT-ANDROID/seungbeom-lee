package org.sopt.and.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object SignUp

    @Serializable
    data object LogIn
}