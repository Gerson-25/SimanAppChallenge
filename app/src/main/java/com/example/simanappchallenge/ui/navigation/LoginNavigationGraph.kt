package com.example.simanappchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.simanappchallenge.ui.screens.login.view.LoginScreen
import com.example.simanappchallenge.ui.screens.signup.view.SignUpScreen

@Composable
fun LoginNavigationGraph(
    navController: NavHostController,
    modifier: Modifier,
    onNavigateHome: () -> Unit
) {
    NavHost(navController = navController, startDestination = "login", modifier = modifier) {
        composable("login") {
             LoginScreen(onNavigateHome = {
                 onNavigateHome()
             }, onNavigationAction = {
                 try {
                     navController.navigate("signup")
                 } catch (e: Exception) {
                     e.printStackTrace()
                 }
             })
        }
        composable("signup") {
             SignUpScreen{
                 onNavigateHome()
             }
        }
    }
}