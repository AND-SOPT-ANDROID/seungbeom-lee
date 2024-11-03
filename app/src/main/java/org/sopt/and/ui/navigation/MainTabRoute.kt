package org.sopt.and.ui.navigation

import kotlinx.serialization.Serializable

sealed interface MainTabRoute : Route {
    @Serializable
    data class MyProFile(
        val email: String,
        val password: String
    ) : MainTabRoute

    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object Home : MainTabRoute

}