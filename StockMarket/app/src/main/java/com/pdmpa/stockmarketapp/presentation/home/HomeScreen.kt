package com.pdmpa.stockmarketapp.presentation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pdmpa.stockmarketapp.R
import com.pdmpa.stockmarketapp.navigation.AddNewCard
import com.pdmpa.stockmarketapp.presentation.home.card_completed.AvailableBalanceCardList
import com.pdmpa.stockmarketapp.ui.theme.Typography

@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .padding(
                    contentPadding
                )
                .fillMaxSize()
        ) {
            ProfileCard(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                viewModel = viewModel
            )

            DeckableLayout(modifier = Modifier.weight(0.8f)) {
                AvailableBalanceCardList()
            }
            AddNewCard(
                modifier = Modifier
                    .weight(0.32f)
                    .padding(16.dp)
                    .fillMaxWidth(),
                navController = navController
            )
            Spacer(modifier = Modifier.weight(0.08f))
        }
    }
}


@Composable
fun AddNewCard(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Card(
        modifier = modifier,
        elevation = 0.dp,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(AddNewCard.route)
            }) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawPath(
                    path = Path().apply {
                        addRoundRect(
                            roundRect = RoundRect(
                                left = 0f,
                                top = 0f,
                                right = size.width,
                                bottom = size.height,
                            )
                        )
                    }, style = Stroke(
                        width = 5f, join = StrokeJoin.Round,
                        miter = 0f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(15f, 20f),
                            phase = 5f
                        )
                    ),
                    color = Color.Gray
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add new card",
                    tint = Color.Gray
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.add_new_cards),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileCard(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    androidx.compose.material3.Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(Color.Transparent),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.img_6),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                androidx.compose.material3.Text(text = viewModel.myGreetingMessage())

                viewModel.getUserName()
                    ?.let { androidx.compose.material3.Text(text = it, style = Typography.body1) }
            }
            Spacer(Modifier.weight(1f))
//            androidx.compose.material3.BadgedBox(
//                badge = { androidx.compose.material3.Badge(modifier = Modifier.size(12.dp)) {} },
//            ) {
//                val radius = 16.dp
//                val shape = RoundedCornerShape(radius)
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .defaultMinSize(minWidth = radius * 2, minHeight = radius * 2)
//                        .background(
//                            color = Color.LightGray,
//                            shape = shape
//                        )
//                        .clip(shape),
//                ) {
//                    androidx.compose.material3.Icon(
//                        painterResource(id = R.drawable.img_14),
//                        contentDescription = ""
//                    )
//                }
//            }
            IconButton(modifier = Modifier.padding(end = 4.dp), onClick = {}) {
                Box {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "notification"
                    )
                    Icon(
                        modifier = Modifier
                            .size(13.5.dp)
                            .align(Alignment.TopEnd),
                        imageVector = Icons.Rounded.Circle,
                        contentDescription = "",
                        tint = Color(0xFFef5350)
                    )
                }
            }
        }
    }
}