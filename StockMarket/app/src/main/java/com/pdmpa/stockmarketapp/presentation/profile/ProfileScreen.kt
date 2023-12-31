package com.pdmpa.stockmarketapp.presentation.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pdmpa.stockmarketapp.R
import com.pdmpa.stockmarketapp.presentation.profile.Constants.PROFILE_PIC_ID
import com.pdmpa.stockmarketapp.presentation.profile.Constants.USERNAME
import com.pdmpa.stockmarketapp.util.PrimaryButton
import com.pdmpa.stockmarketapp.util.extensions.currentFraction

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    var progress by remember { mutableStateOf(0f) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(PaddingValues(top = 20.dp))

        ) {
            viewModel.getUserName()?.let {
                ProfileHeader(
                    it,
                    profile.avatarUrl,
                    progress,
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val sheetState = rememberBottomSheetState(
                initialValue = BottomSheetValue.Collapsed
            )
            val scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = sheetState
            )
            progress = scaffoldState.currentFraction
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContent = {
                    MoreInfoCard(navController, viewModel)
                },
                sheetBackgroundColor = Color.Transparent,
                sheetElevation = (-1).dp,
                backgroundColor = Color.Transparent,
                sheetPeekHeight = 100.dp
            ) {
            }
        }
    }

}

private val profile = Profile()

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileHeader(
    name: String,
    avatar: Int,
    progress: Float
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {

        Image(
            painter = painterResource(avatar),
            contentDescription = "Contact profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .layoutId(PROFILE_PIC_ID)
        )
        Text(
            text = name,
            modifier = Modifier
                .padding(PaddingValues(top = 20.dp, bottom = 10.dp))
                .layoutId(USERNAME),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun MoreInfoCard(navController: NavController, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 0.dp)
            .height(400.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_drop_up),
                contentDescription = null
            )
        }
        Text(
            stringResource(R.string.bottomsheet_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            stringResource(R.string.bottomsheet_subtitle),
            color = Gray,
            modifier = Modifier.padding(bottom = 50.dp)
        )

        BottomSheetCell(stringResource(R.string.email_title), profile.email)
        BottomSheetCell(stringResource(R.string.phone_title), profile.telephone)
        BottomSheetCell(stringResource(R.string.gender_title), profile.gender)
        PrimaryButton(
            text = stringResource(R.string.sign_out),
            modifier = Modifier,
            onClick = { viewModel.onEvent(ProfileEvent.SignOutClickAction(navController)) }) {
        }

    }
}


@Composable
private fun BottomSheetCell(title: String, value: String) {
    Row(modifier = Modifier.padding(bottom = 12.dp)) {
        Text("$title : ", color = Color.Black, fontWeight = FontWeight.Bold)
        Text(value, color = Gray)
    }
    Divider(thickness = 1.dp, color = Gray)
    Spacer(modifier = Modifier.size(20.dp))
}

object Constants {
    const val PROFILE_PIC_ID = "profile_pic"
    const val USERNAME = "username"
}

