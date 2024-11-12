package org.sopt.and.core.navigation

import kotlinx.serialization.Serializable

sealed interface MainTabRoute : Route {
    @Serializable
    data object MyProFile : MainTabRoute

    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object Home : MainTabRoute
}