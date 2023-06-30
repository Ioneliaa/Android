package com.pdmpa.stockmarketapp.presentation.home.card_completed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.pdmpa.stockmarketapp.R
import com.pdmpa.stockmarketapp.domain.model.Card
import com.pdmpa.stockmarketapp.presentation.CardTextField
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_primary
import java.util.*


@Composable
fun AddNewCardMockBack(
    modifier: Modifier = Modifier,
    cardHolderName: String,
    cvvNumber: String
) {
    val localConfiguration = LocalConfiguration.current
    Card(
        elevation = 4.dp,
        modifier = modifier
            .height((localConfiguration.screenHeightDp * 0.3f).dp)
            .fillMaxWidth(),
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Box(
            modifier = Modifier.background(
                Brush.verticalGradient(listOf(Color(color = 0xFF402ED2), Color(color = 0xFF3B9CF6)))
            )
        ) {
        }
        Canvas(modifier = Modifier.fillMaxWidth()) {
            drawLine(
                color = Color(0XFF1F1BC0),
                strokeWidth = 0.2f * size.height,
                start = Offset(x = 0f, y = 0.2f * size.height),
                end = Offset(x = size.width, y = 0.2f * size.height)
            )

            drawPath(
                path = Path().apply {
                    moveTo(x = 0.4f * size.width, y = 0f)
                    cubicTo(
                        x1 = 0.34f * size.width,
                        y1 = 0.34f * size.height,
                        x2 = 0.94f * size.width,
                        y2 = 0.52f * size.height,
                        x3 = 0.88f * size.width, y3 = size.height
                    )
                },
                color = Color(color = 0xFFF79E1B),
                style = Stroke(
                    width = 8f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = PathEffect.cornerPathEffect(radius = 4f)
                )
            )


            drawPath(
                path = Path().apply {
                    moveTo(x = 0.56f * size.width, y = 0f)
                    cubicTo(
                        x1 = 0.8f * size.width,
                        y1 = 0.34f * size.height,
                        x2 = 0.49f * size.width,
                        y2 = 0.66f * size.height,
                        x3 = 0.64f * size.width,
                        y3 = size.height
                    )
                },
                color = Color(color = 0xFF4F74F5),
                style = Stroke(
                    width = 8f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = PathEffect.cornerPathEffect(radius = 4f)
                )
            )
        }
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val (box, name, emvChip) = createRefs()

            val context = LocalContext.current
            Image(
                modifier = Modifier
                    .constrainAs(emvChip) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .size(50.dp),
                painter = rememberDrawablePainter(drawable = context.getDrawable(R.drawable.emv_chip)),
                contentDescription = stringResource(R.string.chip)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(box) {
                        linkTo(start = parent.start, end = parent.end)
                        linkTo(top = parent.top, bottom = parent.bottom)
                    }
            ) {
                Canvas(modifier = Modifier.matchParentSize(), onDraw = {
                    drawRoundRect(
                        color = Color(color = 0xFFF2F1FF),
                        cornerRadius = CornerRadius(x = 10f, y = 10f),
                        size = Size(width = size.width, height = size.height)
                    )

                    drawRoundRect(
                        color = Color(color = 0xFFC9C8FF).copy(alpha = 0.85f),
                        cornerRadius = CornerRadius(x = 5f, y = 5f),
                        size = Size(width = size.width, height = 0.2f * size.height),
                        topLeft = Offset(x = 0f, y = 0.2f * size.height)
                    )

                    drawRoundRect(
                        color = Color(color = 0xFFC9C8FF).copy(alpha = 0.85f),
                        cornerRadius = CornerRadius(x = 5f, y = 5f),
                        size = Size(width = size.width, height = 0.2f * size.height),
                        topLeft = Offset(x = 0f, y = 0.6f * size.height)
                    )

                    drawRoundRect(
                        color = Color(color = 0xFFC9C8FF),
                        cornerRadius = CornerRadius(x = 5f, y = 5f),
                        size = Size(width = 0.18f * size.width, height = size.height),
                        topLeft = Offset(x = 0.82f * size.width, y = 0f)
                    )

                })
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterEnd),
                    text = cvvNumber,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                modifier = Modifier.constrainAs(
                    name
                ) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
                text = cardHolderName.uppercase(Locale.ENGLISH),
                fontWeight = FontWeight.SemiBold
            )

        }


    }
}

@ExperimentalAnimationApi
@Composable
fun AddNewCardMockFront(
    modifier: Modifier = Modifier,
    cardHolderName: String,
    cardNumberValue: String,
    validUntilDate: String
) {
    val localConfiguration = LocalConfiguration.current
    Card(
        elevation = 4.dp,
        modifier = modifier
            .height((localConfiguration.screenHeightDp * 0.3f).dp)
            .fillMaxWidth(),
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Box(
            modifier = Modifier.background(
                Brush.verticalGradient(listOf(Color(color = 0XFF4266FE), Color(color = 0xFF3B9CF6)))
            )
        ) {
        }
        Canvas(modifier = Modifier.fillMaxWidth()) {
            drawPath(
                path = Path().apply {
                    moveTo(x = 0f, y = 0.06f * size.height)
                    cubicTo(
                        x1 = 0.11f * size.width,
                        y1 = 0.24f * size.height,
                        x2 = 0.26f * size.width,
                        y2 = 0.24f * size.height,
                        x3 = 0.32f * size.width, y3 = 0f
                    )
                },
                color = md_theme_light_primary,
                style = Stroke(
                    width = 40f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = PathEffect.cornerPathEffect(15f)
                )
            )
            drawPath(
                path = Path().apply {
                    moveTo(x = 0.6f * size.width, y = 0f)
                    cubicTo(
                        x1 = 0.52f * size.width,
                        y1 = 0.19f * size.height,
                        x2 = 0.88f * size.width,
                        y2 = 0.42f * size.height,
                        x3 = size.width, y3 = 0.67f * size.height
                    )
                },
                color = Color(color = 0xFFF79E1B),
                style = Stroke(
                    width = 8f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = PathEffect.cornerPathEffect(radius = 4f)
                )
            )


            drawPath(
                path = Path().apply {
                    moveTo(x = 0.80f * size.width, y = 0f)
                    cubicTo(
                        x1 = 1f * size.width,
                        y1 = 0.2f * size.height,
                        x2 = 0.59f * size.width,
                        y2 = 0.86f * size.height,
                        x3 = 0.89f * size.width,
                        y3 = size.height
                    )
                },
                color = Color(color = 0xFF4F74F5),
                style = Stroke(
                    width = 8f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = PathEffect.cornerPathEffect(radius = 4f)
                )
            )
            if (cardHolderName.isBlank() && cardNumberValue.isBlank() && validUntilDate.isBlank()) {
                drawRoundRect(
                    color = Color.White,
                    topLeft = Offset(x = 0.82f * size.width, y = 0.1f * size.height),
                    size = Size(width = 0.12f * size.width, height = 0.095f * size.height),
                    cornerRadius = CornerRadius(x = 6f, y = 6f)
                )
            }
            drawPath(
                path = Path().apply {
                    moveTo(x = 0f, y = 0.68f * size.height)
                    quadraticBezierTo(
                        x1 = 0.2f * size.width,
                        y1 = 0.70f * size.height,
                        x2 = 0.38f * size.width,
                        y2 = size.height
                    )
                    lineTo(x = 0f, y = size.height)
                },
                brush = Brush.linearGradient(
                    listOf(Color(color = 0XFF4266FE), Color(color = 0xFF3B9CF6)),
                    tileMode = TileMode.Mirror
                )
            )

            drawPath(
                path = Path().apply {
                    moveTo(x = 0.34f * size.width, y = size.height)
                    lineTo(x = 0.54f * size.width, y = 0.86f * size.height)
                    lineTo(x = 0.54f * size.width, y = size.height)
                },
                color = Color(color = 0xFFFFA58C),
                style = Stroke(
                    width = 5f, cap = StrokeCap.Round, join = StrokeJoin.Round,
                    pathEffect = PathEffect.cornerPathEffect(radius = 2f)
                )
            )
            drawPath(
                path = Path().apply {
                    moveTo(x = 0.34f * size.width, y = size.height)
                    lineTo(x = 0.54f * size.width, y = 0.86f * size.height)
                    lineTo(x = 0.54f * size.width, y = size.height)
                },
                color = Color(color = 0xFFFFA58C)
            )

        }
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val (cardHolderNameLabel, validTill, cardNumber, visa,
                emvChip) = createRefs()

            val context = LocalContext.current
            Image(
                modifier = Modifier
                    .constrainAs(emvChip) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .size(50.dp),
                painter = rememberDrawablePainter(drawable = context.getDrawable(R.drawable.emv_chip)),
                contentDescription = "Emv chip"
            )

            AnimatedVisibility(
                modifier = Modifier.constrainAs(
                    visa
                ) {
                    end.linkTo(parent.end, margin = 4.dp)
                    top.linkTo(parent.top, margin = 4.dp)
                },
                enter = fadeIn(),
                exit = fadeOut(),
                visible = cardHolderName.isNotBlank() || cardNumberValue.isNotBlank() || validUntilDate.isNotBlank()
            ) {
                Text(
                    text = "VISA", style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold
                )
            }

            CardTextField(
                modifier = Modifier.constrainAs(cardHolderNameLabel) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(validTill.start, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
                label = stringResource(R.string.cardholder_name),
                value = if (cardHolderName.length < 25) cardHolderName else "${
                    cardHolderName.take(
                        25
                    )
                }..."
            )



            CardTextField(
                modifier = Modifier.constrainAs(validTill) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(cardHolderNameLabel.end)
                    width = Dimension.wrapContent
                },
                value = validUntilDate.chunked(2).joinToString("/"),
                label = "VALID TILL"
            )

            val formattedCardNumber = cardNumberValue.chunked(4).joinToString(" ")

            Text(modifier = Modifier.constrainAs(cardNumber) {
                start.linkTo(parent.start)
                linkTo(top = parent.top, bottom = parent.bottom)
            }, text = formattedCardNumber, maxLines = Int.MAX_VALUE)
        }

    }

}


@Composable
fun MasterCardMock(modifier: Modifier = Modifier, card: Card) {
    val context = LocalContext.current
    Card(
        elevation = 4.dp,
        modifier = modifier
            .padding(start = 16.dp, end = 6.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Canvas(modifier = Modifier.fillMaxWidth()) {
            drawPath(
                path = Path().apply {
                    moveTo(x = 0f, y = 0.2f * size.height)
                    quadraticBezierTo(
                        x1 = 0.2f * size.width,
                        y1 = 0.62f * size.height,
                        x2 = 0f,
                        y2 = 0.9f * size.height
                    )
                },
                color = Color(color = 0xFF34D39A)
            )

            drawPath(
                path = Path().apply {
                    moveTo(x = 0.3f * size.width, y = size.height)
                    lineTo(x = 0.298f * size.width, y = 0.96f * size.height)
                    lineTo(x = 0.54f * size.width, y = 0.68f * size.height)
                    quadraticBezierTo(
                        x1 = 0.56f * size.width,
                        y1 = 0.65f * size.height,
                        x2 = 0.60f * size.width,
                        y2 = 0.69f * size.height
                    )
                    lineTo(x = 0.615f * size.width, y = size.height)
                },
                color = Color(color = 0xFFFFA58C),
            )

            drawPath(path = Path().apply {
                moveTo(x = 0.3f * size.width, y = 0f)
                lineTo(x = 0.3f * size.width, y = 0.34f * size.height)
                cubicTo(
                    x1 = 0.45f * size.width,
                    y1 = 0.24f * size.height,
                    x2 = 0.55f * size.width,
                    y2 = 0.1f * size.height,
                    x3 = 0.50f * size.width,
                    y3 = 0f
                )
            }, color = Color(color = 0xFF4266FE))

            drawPath(
                path = Path().apply {
                    moveTo(x = 0.55f * size.width, y = 0f)
                    cubicTo(
                        x1 = 0.6f * size.width,
                        y1 = 0.15f * size.height,
                        x2 = 0.77f * size.width,
                        y2 = 0.04f * size.height,
                        x3 = size.width,
                        y3 = 0.3f * size.height
                    )
                },
                style = Stroke(
                    width = 5f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                ),
                color = Color(color = 0xFFF79E1B)
            )

            drawPath(
                path = Path().apply {
                    moveTo(x = 0.73f * size.width, y = 0f)
                    cubicTo(
                        x1 = 0.66f * size.width,
                        y1 = 0.25f * size.height,
                        x2 = 0.99f * size.width,
                        y2 = 0.76f * size.height,
                        x3 = 0.94f * size.width,
                        y3 = size.height
                    )
                },
                style = Stroke(
                    width = 5f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                ),
                color = Color(color = 0XFF4266FE)
            )

        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(16.dp)
                    .size(54.dp),
                painter = rememberDrawablePainter(
                    drawable = ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_mc_symbol
                    )
                ), contentDescription = ""
            )
        }
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val (balance, amount, cardNumber, date) = createRefs()
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "Balance",
                    modifier = Modifier.constrainAs(balance) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }, style = MaterialTheme.typography.body2
                )
            }
            Text(
                text = card.balance,
                modifier = Modifier.constrainAs(amount) {
                    top.linkTo(balance.bottom)
                    start.linkTo(balance.start)
                }, style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "****  " + card.number.takeLast(4),
                modifier = Modifier.constrainAs(cardNumber) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )

            Text(
                text = card.validUntil.take(2) + "/" + card.validUntil.takeLast(2),
                modifier = Modifier.constrainAs(date) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
        }
    }
}