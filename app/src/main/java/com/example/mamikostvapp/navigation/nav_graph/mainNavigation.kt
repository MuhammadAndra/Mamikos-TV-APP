package com.example.mamikostvapp.navigation.nav_graph

import androidx.lifecycle.SavedStateHandle
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
data class ShowDetail(val id: Int) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<ShowDetail>()
    }
}

fun NavGraphBuilder.mainGraph(navController: NavController) {

    composable<ShowList> {
        ShowListScreen(
            onNavigateToDetail = { id ->
                navController.navigate(ShowDetail(id))
            }
        )
    }

    composable<ShowDetail> {
        ShowDetailScreen(onBackClick = {navController.navigateUp()})
    }
}