package com.example.mamikostvapp.ui.screen.show_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil3.compose.AsyncImage
import com.example.mamikostvapp.data.model.dummyShowDetail

@Composable
fun ShowDetailScreen(modifier: Modifier = Modifier) {
    val dummyShowDetail = dummyShowDetail
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = { ShowDetailTopBar(onBackClick = {}, onShareClick = {}) },
        containerColor = Color(0xFF242A32)
    ) { innerPadding ->
       // ShowDetailScreenSkeleton(modifier = Modifier.padding(innerPadding))
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                AsyncImage(
                    model = dummyShowDetail.image,
                    contentDescription = "image of ${dummyShowDetail.name}",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            item {
                Text(
                    dummyShowDetail.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            item {
                PremiereDateCard(
                    premiereDate = dummyShowDetail.premiered,
                    rating = dummyShowDetail.rating
                )
            }
            item {
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
                        Text("Summary", fontSize = 20.sp, color = Color.White)
                    }
                    Tab(
                        modifier = Modifier.height(36.dp),
                        selected = selectedTabIndex == 1,
                        onClick = { selectedTabIndex = 1 }) {
                        Text("Season", fontSize = 20.sp, color = Color.White)
                    }
                    Tab(
                        modifier = Modifier.height(36.dp),
                        selected = selectedTabIndex == 2,
                        onClick = { selectedTabIndex = 2 }) {
                        Text("Cast", fontSize = 20.sp, color = Color.White)
                    }

                }
                when (selectedTabIndex) {
                    0 -> {
                        Text(
                            dummyShowDetail.summary.cleanHtml(),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Justify,
                            color = Color.White,
                            modifier = Modifier.padding(top = 12.dp),
                        )
                    }

                    1 -> {
                        Text(
                            "Season", fontSize = 16.sp,
                            textAlign = TextAlign.Justify,
                            color = Color.White,
                            modifier = Modifier.padding(top = 12.dp),
                        )
                    }

                    2 -> {
                        Text(
                            "Cast", fontSize = 16.sp,
                            textAlign = TextAlign.Justify,
                            color = Color.White,
                            modifier = Modifier.padding(top = 12.dp),
                        )
                    }
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