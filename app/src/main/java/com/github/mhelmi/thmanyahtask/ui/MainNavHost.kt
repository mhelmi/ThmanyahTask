package com.github.mhelmi.thmanyahtask.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mhelmi.thmanyahtask.ui.home.HomeScreen
import com.github.mhelmi.thmanyahtask.ui.search.SearchScreen

@Composable
fun MainNavHost() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = "home"
  ) {
    composable("home") {
      HomeScreen(navController = navController)
    }
    composable("search") {
      SearchScreen(navController = navController)
    }
  }
}