package com.pdmpa.stockmarketapp.presentation.home.card_completed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pdmpa.stockmarketapp.R
import com.pdmpa.stockmarketapp.ui.theme.Typography
import com.pdmpa.stockmarketapp.ui.theme.md_theme_dark_onPrimaryContainer
import com.pdmpa.stockmarketapp.util.cardList
import kotlinx.coroutines.delay
import java.text.DecimalFormat


private val decimalFormatter = DecimalFormat("#,###")

@ExperimentalAnimationApi
@Composable
fun AvailableBalanceCardList() {
    Column(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(R.string.available_balance),
                style = Typography.body2,
                fontWeight = FontWeight.Bold
            )
        }
        AvailableBalanceText()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(md_theme_dark_onPrimaryContainer)
                .padding(start = 16.dp, top = 24.dp, bottom = 16.dp),
            text = "My cards",
            fontWeight = FontWeight.SemiBold
        )
        CardList()
    }
}

@ExperimentalAnimationApi
@Composable
private fun CardList() {
    Box {
        val isCardListVisible = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }

        AnimatedVisibility(
            visibleState = isCardListVisible,
            enter = slideInHorizontally(
                initialOffsetX = { +it * 2 },
                animationSpec = tween(
                    durationMillis = 1200,
                    easing = LinearOutSlowInEasing
                )
            )
        ) {
            val localConfiguration = LocalConfiguration.current
            val cardsHeight =
                remember { (localConfiguration.screenHeightDp * 0.25f) }
            val cardsWidth = remember { (localConfiguration.screenWidthDp * 0.80f) }
            LazyRow {
                items(cardList.size) {
                    when (it) {
                        0 -> MasterCardMock(
                            modifier = Modifier
                                .height(cardsHeight.dp)
                                .width(cardsWidth.dp),
                            cardList[it]
                        )
                        2 ->
                            MasterCardMock(
                                modifier = Modifier
                                    .height(cardsHeight.dp)
                                    .width(cardsWidth.dp),
                                cardList[it]
                            )
                        else -> {
                            if (it % 2 == 1){
                                Spacer(modifier = Modifier.width(12.dp))
                            }
                        }
                    }


                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
fun AvailableBalanceText() {
    var animateBalanceCount: Boolean by remember { mutableStateOf(false) }
    val availableBalance: Float by animateFloatAsState(
        targetValue = if (animateBalanceCount) 52739.94f else 25000.33F,
        animationSpec = tween(
            durationMillis = 1250
        )
    )
    LaunchedEffect(key1 = true, block = {
        delay(200)
        animateBalanceCount = true
    })
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
        text = "$${decimalFormatter.format(availableBalance)}",
        style = Typography.h4,
        fontWeight = FontWeight.SemiBold
    )
}

