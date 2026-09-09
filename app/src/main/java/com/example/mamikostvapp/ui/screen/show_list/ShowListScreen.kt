package com.example.mamikostvapp.ui.screen.show_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mamikostvapp.data.model.ShowListItem
import com.example.mamikostvapp.data.model.dummyShows
import com.example.saferecycle.ui.state.UiState

@Composable
fun ShowListScreen(modifier: Modifier = Modifier) {
    val vm: ShowListViewmodel = viewModel()
    val showsState by vm.shows.collectAsState()

    LaunchedEffect(Unit) {
        vm.loadShows()
    }
    Scaffold(containerColor = Color(0xFF242A32)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "What do you want to watch?",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            LazyVerticalGrid(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                columns = GridCells.Fixed(2)
            ) {
                when (showsState) {
                    is UiState.Loading -> items(12) { ShowListCardSkeleton() }
                    is UiState.Success -> {
                        val shows = (showsState as UiState.Success).data
                        items(shows){show->
                            ShowListCard(item = show)
                        }
                    }
                    //implement error
                    else -> {}
                }
            }
        }
    }
}