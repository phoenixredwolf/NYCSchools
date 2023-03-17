package com.phoenixredwolf.barretvogtmannycschools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuScreen(
    val route: String,
    val icon: ImageVector,
    val title: String
    ) {
    object Home: BottomMenuScreen(
        "home",
        icon = Icons.Outlined.Home,
        "Home"
    )

    object Search: BottomMenuScreen(
        "search",
        icon = Icons.Outlined.Search,
        "Search"
    )

    object Saved: BottomMenuScreen(
        "saved",
        icon = Icons.Outlined.StarOutline,
        "Saved"
    )
}