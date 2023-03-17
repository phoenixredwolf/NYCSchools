package com.phoenixredwolf.barretvogtmannycschools.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phoenixredwolf.barretvogtmannycschools.BottomMenuScreen
import com.phoenixredwolf.barretvogtmannycschools.components.BottomMenu
import com.phoenixredwolf.barretvogtmannycschools.data.model.School
import com.phoenixredwolf.barretvogtmannycschools.ui.screen.DetailScreen
import com.phoenixredwolf.barretvogtmannycschools.ui.screen.Home
import com.phoenixredwolf.barretvogtmannycschools.ui.viewmodel.MainViewModel

@Composable
fun SchoolApp(mainViewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()

    MainScreen(navController,scrollState,mainViewModel)
}

@Composable
fun MainScreen(
    navController: NavHostController,
    scrollState: ScrollState,
    mainViewModel: MainViewModel
) {
    Scaffold(
        bottomBar = {
            BottomMenu(navController = navController)
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Navigation(
            navController = navController,
            scrollState = scrollState,
            paddingValues = it,
            viewModel = mainViewModel
        )

    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.isError.collectAsState()
    val schools = mutableListOf(School())
    val allSchools = viewModel.schoolsResponse.collectAsState().value.school
    schools.addAll(allSchools ?: listOf())
    Log.d("Initial Schools", "$schools")
    NavHost(
        navController = navController,
        startDestination = BottomMenuScreen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        val isLoading = mutableStateOf(loading)
        val isError = mutableStateOf(error)
        bottomNavigation(navController,schools,viewModel,isLoading,isError)
        composable("Detail/{index}",
            arguments = listOf(navArgument("index"){type = NavType.IntType})
        ) { navBackStackEntry ->
            val index = navBackStackEntry.arguments?.getInt("index")
            index?.let {
                schools.clear()
                schools.addAll(viewModel.schoolsResponse.value.school ?: emptyList())
                val school = schools[index]
                DetailScreen(navController = navController, scrollState = scrollState, school = school)
            }
        }
    }

}

fun NavGraphBuilder.bottomNavigation(
    navController: NavController,
    schools: List<School>,
    viewModel: MainViewModel,
    isLoading: MutableState<Boolean>,
    isError: MutableState<Boolean>
) {
    composable(BottomMenuScreen.Home.route) {
        Home(
            navController = navController,
            schools,
            viewModel = viewModel,
            isLoading,
            isError
        )
    }
    composable(BottomMenuScreen.Search.route) {

    }
    composable(BottomMenuScreen.Saved.route) {

    }
}