package com.pdmpa.stockmarketapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pdmpa.stockmarketapp.ui.theme.md_theme_dark_onPrimaryContainer

@Composable
fun DeckableLayout(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {}
) {

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (upperBackground, columnContent) = createRefs()
        val upperBackgroundGuideline = createGuidelineFromTop(fraction = 0.62f)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(
                    upperBackground
                ) {
                    linkTo(start = parent.start, end = parent.end)
                    linkTo(top = parent.top, bottom = upperBackgroundGuideline)
                    height = Dimension.fillToConstraints
                },
            color = md_theme_dark_onPrimaryContainer,
            content = {}
        )
        Column(
            modifier = Modifier.constrainAs(columnContent) {
                top.linkTo(parent.top)
                linkTo(start = parent.start, end = parent.end)
                width = Dimension.fillToConstraints
            },
            content = {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.onPrimary) {
                    content()
                }
            })
    }
}