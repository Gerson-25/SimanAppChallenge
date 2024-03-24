package com.example.simanappchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.simanappchallenge.ui.screens.home.details.view.DetailsScreen
import com.example.simanappchallenge.ui.screens.home.favorites.view.FavoriteScreen
import com.example.simanappchallenge.ui.screens.home.list.view.ListScreen

@Composable
fun HomeNavigationGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListScreen(onGoFavorites = {
                navController.navigate("favorites")
            }){
                navController.navigate("details/$it")
            }
        }

        composable(
            route = "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
            ){navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 0
             DetailsScreen(id = id)
        }

        composable("favorites") {
             FavoriteScreen{
                navController.navigate("details/$it")
             }
        }
    }
}