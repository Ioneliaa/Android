package com.pdmpa.stockmarketapp.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pdmpa.stockmarketapp.R
import com.pdmpa.stockmarketapp.navigation.CardCreated
import com.pdmpa.stockmarketapp.navigation.Home
import com.pdmpa.stockmarketapp.navigation.StocksList
import com.pdmpa.stockmarketapp.ui.theme.Typography
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_onBackground
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_primary
import kotlinx.coroutines.delay


@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        androidx.compose.material3.Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = Typography.h6,
            color = md_theme_light_onBackground,
            modifier = modifier
        )
    }
}

@Composable
fun AddNewCardScreenTopAppBar(
    navController: NavController
) {
    Row(
        modifier = Modifier.height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            navController.navigate(Home.route) {
                popUpTo(Home.route, popUpToBuilder = {
                    inclusive = true
                })
            }
        }, modifier = Modifier.weight(0.12f)) {
            CompositionLocalProvider(LocalContentAlpha provides 0.4f) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_to_homepage)
                )
            }
        }
        Text(
            modifier = Modifier.weight(0.82f),
            text = "Add new card",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.weight(0.12f))
    }
}

@Composable
fun CompanyInfoTopAppBar(
    navController: NavController,
    title: String?,
    onCLick: () -> Unit
) {
    Row(
        modifier = Modifier.height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            navController.navigate(StocksList.route) {
                popUpTo(StocksList.route, popUpToBuilder = {
                    inclusive = true
                })
            }
        }, modifier = Modifier.weight(0.12f)) {
            CompositionLocalProvider(LocalContentAlpha provides 0.4f) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_back)
                )
            }
        }
        if (title != null) {
            Text(
                modifier = Modifier.weight(0.82f),
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.SemiBold)
            )
        }
        Spacer(modifier = Modifier.weight(0.12f))
        IconButton(onClick = onCLick, modifier = Modifier.weight(0.12f)) {
            CompositionLocalProvider(LocalContentAlpha provides 0.4f) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share"
                )
            }
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun CardInputCollector(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    keyboardActions: KeyboardActions,
    imeAction: ImeAction
) {
    val focusRequester = remember { FocusRequester() }
    val inputMethodService = LocalTextInputService.current
    LaunchedEffect(key1 = true, block = {
        delay(timeMillis = 700)
        focusRequester.requestFocus()
        inputMethodService?.showSoftwareKeyboard()
    })

    Card(
        modifier = modifier.fillMaxWidth(), elevation = 2.dp,
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(4.dp))
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .padding(8.dp)
        ) {
            TextField(
                modifier = Modifier.focusRequester(focusRequester),
                value = value, onValueChange = { onValueChange(it) }, label = {
                    Text(text = label)
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onBackground,
                    focusedLabelColor = MaterialTheme.colors.onBackground.copy(alpha = 0.4f)
                ),
                singleLine = true,
                keyboardActions = keyboardActions,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction,
                    capitalization = KeyboardCapitalization.Characters
                )
            )
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun AddOrCancelCardButtonGroup(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val showButtonsText = remember {
        MutableTransitionState(initialState = false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = showButtonsText,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate(CardCreated.route) },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 14.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = md_theme_light_primary,
                    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                )
            ) {
                Text(
                    modifier = Modifier.animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { +it },
                            animationSpec = tween(durationMillis = 500)
                        )
                    ),
                    text = stringResource(R.string.add_card),
                    fontWeight = FontWeight.SemiBold
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
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
                )
            ) {
                Text(
                    modifier = Modifier.animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { +it },
                            animationSpec = tween(durationMillis = 500)
                        )
                    ),
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CardTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    val labelFontSize by animateFloatAsState(targetValue = if (value.isBlank()) 14f else 12f)
    Column(modifier = modifier) {
        CompositionLocalProvider(LocalContentAlpha provides 0.4f) {
            Text(
                text = label,
                style = MaterialTheme.typography.caption,
                fontSize = labelFontSize.sp
            )
        }
        AnimatedVisibility(
            visible = value.isNotBlank(),
            enter = slideInVertically(initialOffsetY = { +it / 2 })
        ) {
            Text(
                text = value, maxLines = 1,
                style = MaterialTheme.typography.body2
            )
        }
    }
}