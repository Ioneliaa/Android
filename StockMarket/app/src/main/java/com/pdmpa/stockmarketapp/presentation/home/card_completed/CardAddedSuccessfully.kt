package com.pdmpa.stockmarketapp.presentation.home.card_completed

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pdmpa.stockmarketapp.navigation.Home
import com.pdmpa.stockmarketapp.ui.theme.Typography
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_primary


@ExperimentalAnimationApi
@Composable
fun CardAddedSuccessfully(
    navController: NavController
) {
    val showCardAddedSuccessfully = remember {
        MutableTransitionState(initialState = false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = showCardAddedSuccessfully,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            CongratulationsCard(
                modifier = Modifier.animateEnterExit(
                    enter = expandVertically(
                        expandFrom = Alignment.CenterVertically,
                        initialHeight = { it / 4 },
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = LinearOutSlowInEasing
                        )
                    )
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route, popUpToBuilder = {
                            inclusive = true
                        })
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 14.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = md_theme_light_primary
                )
            ) {
                Text(
                    modifier = Modifier.animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { +it * 2 },
                            animationSpec = tween(delayMillis = 800, durationMillis = 800)
                        )
                    ),
                    text = stringResource(com.pdmpa.stockmarketapp.R.string.back_to_wallet),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun CongratulationsCard(modifier: Modifier = Modifier) {
    val showCongratulationsCard = remember {
        MutableTransitionState(initialState = false).apply {
            targetState = true
        }
    }
    val transition = updateTransition(targetState = showCongratulationsCard, label = "")
    val arcSweepAngle = transition.animateFloat(label = "arcSweepAngle",
        transitionSpec = {
            tween(
                durationMillis = 800
            )
        }) { state ->
        if (state.currentState == showCongratulationsCard.targetState) {
            -340f
        } else {
            0f
        }
    }

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val cardHeight = remember(screenHeight) { screenHeight * 0.3f }
    AnimatedVisibility(
        visibleState = showCongratulationsCard,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(cardHeight.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Canvas(modifier = Modifier
                    .size(100.dp), onDraw = {
                    drawArc(
                        color = md_theme_light_primary,
                        startAngle = 315f,
                        sweepAngle = arcSweepAngle.value,
                        useCenter = false,
                        style = Stroke(
                            width = 8f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round,
                            pathEffect = PathEffect.cornerPathEffect(radius = 5f)
                        )
                    )

                    drawPath(
                        path = Path().apply {
                            moveTo(
                                x = 0.24f * size.width,
                                y = 0.5f * size.height
                            )
                            lineTo(x = 0.45f * size.width, y = 0.73f * size.height)
                            lineTo(x = size.width, y = 0.12f * size.height)
                        },
                        color = md_theme_light_primary,
                        style = Stroke(
                            width = 10f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round,
                            pathEffect = PathEffect.cornerPathEffect(radius = 8f)
                        )
                    )
                })

                Text(
                    text = stringResource(com.pdmpa.stockmarketapp.R.string.congratulations),
                    modifier = Modifier
                        .animateEnterExit(
                            enter = fadeIn(animationSpec = tween(durationMillis = 600))
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    style = Typography.body1.copy(
                        color = md_theme_light_primary,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "Your card has been added successfully",
                    style = Typography.body2,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .animateEnterExit(
                            enter = fadeIn(animationSpec = tween(durationMillis = 600)) + slideInVertically(
                                initialOffsetY = { +it },
                                animationSpec = tween(durationMillis = 800, delayMillis = 800)
                            )
                        )
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}
