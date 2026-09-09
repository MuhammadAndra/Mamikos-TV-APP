package com.example.mamikostvapp.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mamikostvapp.ui.screen.show_detail.ShowDetailScreen
import com.example.mamikostvapp.ui.screen.show_list.ShowListScreen
import kotlinx.serialization.Serializable

@Serializable
object ShowList

@Serializable
data class ShowDetail(val id: Int)

fun NavGraphBuilder.mainGraph(navController: NavController) {

    composable<ShowList> {
        ShowListScreen()
    }

    composable<ShowDetail> { backStackEntry ->
        val showDetail = backStackEntry.toRoute<ShowDetail>()
        ShowDetailScreen()
    }
}