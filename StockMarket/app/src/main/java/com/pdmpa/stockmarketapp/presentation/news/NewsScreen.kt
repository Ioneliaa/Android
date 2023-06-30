package com.pdmpa.stockmarketapp.presentation.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.pdmpa.stockmarketapp.presentation.CommonTopBar

@ExperimentalMaterialApi
@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    remember { mutableStateOf(TextFieldValue("")) }
    val state = newsViewModel.newsState.value
    val uriHandler = LocalUriHandler.current
    val searchCoin = remember { mutableStateOf(TextFieldValue("")) }
    val isRefreshing by newsViewModel.isRefresh.collectAsState()

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Column {
            CommonTopBar(title = "Trending News")
            Row(
                modifier = Modifier.padding(12.dp)
            ) {
                val isBeingSearched = searchCoin.value.text
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                    onRefresh = { newsViewModel.refresh() }) {

                    LazyColumn {
                        items(state.news.filter {
                            it.title.contains(isBeingSearched, ignoreCase = true) ||
                                    it.description.contains(isBeingSearched, ignoreCase = true)
                        }, key = { it.title }) { news ->
                            NewsCard(
                                newsThumb = news.imgURL,
                                title = news.title,
                                onClick = {
                                    uriHandler.openUri(news.link)
                                }
                            )
                            Spacer(Modifier.height(15.dp))
                        }
                    }
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color.Green
            )
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
