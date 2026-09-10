package com.example.mamikostvapp.ui.screen.show_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.mamikostvapp.data.model.Cast
import com.example.mamikostvapp.data.model.Season

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.Transparent,
            titleContentColor = Color.Transparent,
            actionIconContentColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        },
        title = {
            Text(
                "Detail",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = onShareClick) {
                Icon(
                    Icons.Filled.Share,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        }
    )
}

@Composable
fun PremiereDateCard(
    modifier: Modifier = Modifier,
    premiereDate: String,
    rating: Double?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Premiere Date Icon",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
            Text(premiereDate, fontSize = 18.sp, color = Color.Gray)
        }
        VerticalDivider(Modifier.height(18.dp), color = Color.Gray)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating Icon",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
            Text(
                "${rating ?: " No Rating"}",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ShowDetailScreenSkeleton(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(210f / 295f)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color(0xFF303841))

        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(25.dp)
                    .background(
                        shape = RoundedCornerShape(13.dp),
                        color = Color(0xFF303841),
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(18.dp)
                    .background(
                        shape = RoundedCornerShape(13.dp),
                        color = Color(0xFF303841),
                    )
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(13.dp),
                            color = Color(0xFF303841),
                        )
                        .weight(1f)
                        .height(40.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(13.dp),
                            color = Color(0xFF303841),
                        )
                        .weight(1f)
                        .height(40.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(13.dp),
                            color = Color(0xFF303841),
                        )
                        .weight(1f)
                        .height(40.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(350.dp)
                    .background(
                        shape = RoundedCornerShape(13.dp),
                        color = Color(0xFF303841),
                    )
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun ShowSeasonCard(modifier: Modifier = Modifier, season: Season) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = season.image?.medium,
            contentDescription = "image of season ${season.number}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .aspectRatio(446f / 642f)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF303841)
                )
        )
        Column() {
            Text(
                "Season ${season.number}",
                fontSize = 20.sp,
                color = Color.White
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Premiere Date Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
                Text(season.premiereDate?:"-", fontSize = 18.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun ShowCastCard(modifier: Modifier = Modifier, cast: Cast) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = cast.character.image?.medium,
            contentDescription = "image of character ${cast.character.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(400f / 600f)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF303841)
                )
        )
        AsyncImage(
            model = cast.person.image?.medium
                ?: "https://static.tvmaze.com/uploads/images/medium_portrait/0/2404.jpg",
            contentDescription = "image of character ${cast.person.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(400f / 600f)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF303841)
                )
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                cast.person.name,
                fontSize = 20.sp,
                color = Color.White
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Cast Birth Day",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
                Text(cast.person.birthday ?: "-", fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}