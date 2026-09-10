package com.example.mamikostvapp.ui.screen.show_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.mamikostvapp.ui.component.ErrorComponent
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailScreen(
    modifier: Modifier = Modifier,
    vm: ShowDetailViewmodel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val showDetailState by vm.showDetail.collectAsState()
    val showSeasonsState by vm.showSeasons.collectAsState()
    val showCastsState by vm.showCasts.collectAsState()

    //initial load
    LaunchedEffect(Unit) {
        vm.loadShowDetail()
        vm.loadShowSeasons()
        vm.loadShowCasts()
    }

    //handle refresh
    val state = rememberPullToRefreshState()
    var isUserRefreshing by remember { mutableStateOf(false) }
    LaunchedEffect(showDetailState) {
        if (showDetailState !is UiState.Loading) isUserRefreshing = false
    }
    LaunchedEffect(showSeasonsState) {
        if (showSeasonsState !is UiState.Loading) isUserRefreshing = false
    }
    LaunchedEffect(showCastsState) {
        if (showCastsState !is UiState.Loading) isUserRefreshing = false
    }

    //handle Tab
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    //handle Share Action
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ShowDetailTopBar(
                onBackClick = onBackClick,
                onShareClick = {
                    if (showDetailState is UiState.Success) {
                        val show = (showDetailState as UiState.Success).data
                        vm.shareShow(context, show = show)
                    }
                }
            )
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
                vm.loadShowDetail()
                vm.loadShowSeasons()
                vm.loadShowCasts()
            },
            state = state,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = showDetailState is UiState.Loading,
                    containerColor = Color.Transparent,
                    color = Color.White,
                    state = state
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                when (showDetailState) {
                    is UiState.Success -> {
                        item {
                            val showDetail =
                                (showDetailState as UiState.Success).data
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                AsyncImage(
                                    model = showDetail.image.original,
                                    contentDescription = "image of ${showDetail.name}",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .aspectRatio(446f / 642f)
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(
                                            shape = RoundedCornerShape(20.dp),
                                            color = Color(0xFF303841)
                                        )
                                )
                                Text(
                                    showDetail.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                                PremiereDateCard(
                                    premiereDate = showDetail.premiered,
                                    rating = showDetail.rating.average
                                )
                                TabRow(
                                    selectedTabIndex = selectedTabIndex,
                                    containerColor = Color.Transparent,
                                    contentColor = Color.Transparent,
                                    indicator = { tabPositions ->
                                        TabRowDefaults.SecondaryIndicator(
                                            modifier = Modifier.tabIndicatorOffset(
                                                tabPositions[selectedTabIndex]
                                            ),
                                            color = Color.Gray
                                        )
                                    },
                                    divider = {
                                        HorizontalDivider(color = Color.Transparent)
                                    },
                                ) {
                                    Tab(
                                        modifier = Modifier.height(40.dp),
                                        selected = selectedTabIndex == 0,
                                        onClick = { selectedTabIndex = 0 },
                                        unselectedContentColor = Color.Transparent
                                    ) {
                                        Text(
                                            "Summary",
                                            fontSize = 20.sp,
                                            color = Color.White
                                        )
                                    }
                                    Tab(
                                        modifier = Modifier.height(36.dp),
                                        selected = selectedTabIndex == 1,
                                        onClick = { selectedTabIndex = 1 }) {
                                        Text(
                                            "Season",
                                            fontSize = 20.sp,
                                            color = Color.White
                                        )
                                    }
                                    Tab(
                                        modifier = Modifier.height(36.dp),
                                        selected = selectedTabIndex == 2,
                                        onClick = { selectedTabIndex = 2 }) {
                                        Text(
                                            "Cast",
                                            fontSize = 20.sp,
                                            color = Color.White
                                        )
                                    }

                                }
                            }
                            when (selectedTabIndex) {
                                0 -> {
                                    Text(
                                        showDetail.summary.cleanHtml(),
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Justify,
                                        color = Color.White,
                                        modifier = Modifier.padding(top = 12.dp),
                                    )
                                }

                                1 -> {
                                    //when showSeasonState success error, loading,
                                    when (showSeasonsState) {
                                        is UiState.Success -> {
                                            val seasons =
                                                (showSeasonsState as UiState.Success).data
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 12.dp),
                                                verticalArrangement = Arrangement.spacedBy(
                                                    12.dp
                                                )
                                            ) {
                                                seasons.forEach { season ->
                                                    ShowSeasonCard(season = season)
                                                }
                                            }
                                        }

                                        else -> {}
                                    }
                                }

                                2 -> {
                                    //when showCastState success error, loading,
                                    when (showCastsState) {
                                        is UiState.Success -> {
                                            val casts =
                                                (showCastsState as UiState.Success).data
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 12.dp),
                                                verticalArrangement = Arrangement.spacedBy(
                                                    12.dp
                                                )
                                            ) {
                                                casts.forEach { cast ->
                                                    ShowCastCard(cast = cast)
                                                }
                                            }
                                        }

                                        else -> {}
                                    }
                                }
                            }

                        }
                    }

                    is UiState.Loading -> item {
                        ShowDetailScreenSkeleton()
                    }

                    is UiState.Error -> {
                        val error = (showDetailState as UiState.Error).error
                        item {
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

fun String.cleanHtml(): String {
    val decoded = replace(
        Regex("""\\u([0-9a-fA-F]{4})""")
    ) { match ->
        match.groupValues[1].toInt(16).toChar().toString()
    }

    return HtmlCompat
        .fromHtml(decoded, HtmlCompat.FROM_HTML_MODE_LEGACY)
        .toString()
        .trim()
}