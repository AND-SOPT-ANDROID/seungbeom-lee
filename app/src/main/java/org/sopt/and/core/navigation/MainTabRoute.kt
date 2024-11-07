package org.sopt.and.core.navigation

import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.UserToken

sealed interface MainTabRoute : Route {
    @Serializable
    data class MyProFile(
        val userToken: String
    ) : MainTabRoute

    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object Home : MainTabRoute
}