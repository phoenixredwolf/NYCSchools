package com.phoenixredwolf.barretvogtmannycschools.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.phoenixredwolf.barretvogtmannycschools.BottomMenuScreen
import com.phoenixredwolf.barretvogtmannycschools.R

@Composable
fun BottomMenu(navController: NavController) {

    val menuItems = listOf(
        BottomMenuScreen.Home,
        BottomMenuScreen.Search,
        BottomMenuScreen.Saved
    )

    BottomNavigation(contentColor = colorResource(id = R.color.white)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        menuItems.forEach {
            BottomNavigationItem(
                label = { Text( text = it.title ) },
                alwaysShowLabel = true,
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {
                            route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                icon = {
                    Icon ( imageVector = it.icon, contentDescription = it.title )
                }
            )
        }
    }
}