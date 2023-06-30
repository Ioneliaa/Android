package com.pdmpa.stockmarketapp.presentation.listingscompany

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_onPrimaryContainer
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyListingsScreen(
    navController: NavController,
    viewModel: CompanyListingsViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(
            modifier = Modifier.padding(
                horizontal = 16.dp
            )
        )

        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    CompanyListingsEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = md_theme_light_onPrimaryContainer,
                unfocusedBorderColor = md_theme_light_primary
            ),
        )

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(CompanyListingsEvent.Refresh)
            }
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.companies.size) { i ->
                    val company = state.companies[i]
                    CompanyItem(
                        company = company,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onEvent(
                                    CompanyListingsEvent.NavigateToInfo(
                                        company.symbol,
                                        navController
                                    )
                                )
                            }
                            .padding(16.dp)
                    )

                    if (i < state.companies.size) {
                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            )
                        )
                    }
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//private fun ProfileCard(
//    modifier: Modifier = Modifier,
//    viewModel: CompanyListingsViewModel
//) {
//    Card(
//        modifier = modifier,
//        colors = CardDefaults.cardColors(Color.Transparent),
//        shape = RoundedCornerShape(8.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(8.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                modifier = Modifier
//                    .size(48.dp)
//                    .clip(CircleShape),
//                painter = painterResource(R.drawable.img_6),
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
//
//            Column(
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                Text(text = viewModel.myGreetingMessage())
//
//                viewModel.getUserName()?.let { Text(text = it, style = Typography.body1) }
//            }
//            Spacer(Modifier.weight(1f))
//            BadgedBox(
//                badge = { Badge(modifier = Modifier.size(12.dp)) {} },
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
//                    Icon(painterResource(id = R.drawable.img_14), contentDescription = "")
//                }
//            }
//        }
//    }
//}