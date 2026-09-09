package com.example.mamikostvapp.ui.screen.show_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.mamikostvapp.data.model.ShowListItem


@Composable
fun ShowListCard(modifier: Modifier = Modifier, item: ShowListItem) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = "image of ${item.name}",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(210f / 295f)
                .clip(RoundedCornerShape(26.dp))
        )
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                text = item.name,
                color = Color.White,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Filled.Star,
                    contentDescription = "rating icon",
                    tint = Color(0xffFF8700)
                )
                Text(
                    text = " ${item.rating ?: "No Rating"}",
                    color = Color(0xffFF8700),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ShowListCardSkeleton(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(210f / 295f)
                .background(
                    shape = RoundedCornerShape(13.dp),
                    color = Color(0xFF303841),
                )
        )
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(
                        shape = RoundedCornerShape(13.dp),
                        color = Color(0xFF303841),
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(16.dp)
                    .background(
                        shape = RoundedCornerShape(13.dp),
                        color = Color(0xFF303841),
                    )
            )

        }
    }
}