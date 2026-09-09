package com.example.mamikostvapp.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mamikostvapp.ui.screen.show_list.ShowListScreen
import kotlinx.serialization.Serializable

@Serializable
object ShowList

fun NavGraphBuilder.mainGraph(navController: NavController){

    composable<ShowList> {
        ShowListScreen()
    }
}