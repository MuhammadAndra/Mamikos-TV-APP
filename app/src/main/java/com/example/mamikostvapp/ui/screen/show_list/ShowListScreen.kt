package com.example.mamikostvapp.ui.screen.show_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mamikostvapp.data.model.ShowListItem
import com.example.mamikostvapp.data.model.dummyShows
import com.example.mamikostvapp.ui.component.ErrorComponent
import com.example.saferecycle.ui.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowListScreen(modifier: Modifier = Modifier) {
    val vm: ShowListViewmodel = viewModel()
    val showsState by vm.shows.collectAsState()

    //initial load
    LaunchedEffect(Unit) { vm.loadShows() }

    //handle refresh
    val state = rememberPullToRefreshState()
    var isUserRefreshing by remember { mutableStateOf(false) }
    LaunchedEffect(showsState) {
        if (showsState !is UiState.Loading)  isUserRefreshing = false
    }

    Scaffold(
        topBar = {
            ShowListTopBar()
        },
        containerColor = Color(0xFF242A32)
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxSize(),
            isRefreshing = isUserRefreshing,
            onRefresh = {
                isUserRefreshing = true
                vm.loadShows()
            },
            state = state,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = showsState is UiState.Loading,
                    containerColor = Color.Transparent,
                    color = Color.White,
                    state = state
                )
            }
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                columns = GridCells.Fixed(2)
            ) {
                when (showsState) {
                    is UiState.Loading -> items(12) { ShowListCardSkeleton() }
                    is UiState.Success -> {
                        val shows =
                            (showsState as UiState.Success).data
                        items(shows) { show ->
                            ShowListCard(item = show)
                        }
                    }
                    //implement error
                    is UiState.Error -> {
                        val error = (showsState as UiState.Error).error
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            ErrorComponent(
                                modifier = Modifier.height(600.dp),
                                errorMessage = error.message
                                    ?: "Unknown Error"
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}