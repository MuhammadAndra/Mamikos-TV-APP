package com.example.mamikostvapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mamikostvapp.navigation.nav_graph.ShowDetail
import com.example.mamikostvapp.navigation.nav_graph.ShowList
import com.example.mamikostvapp.navigation.nav_graph.mainGraph


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ShowDetail(1)
    ) {
        mainGraph(navController = navController)
    }
}