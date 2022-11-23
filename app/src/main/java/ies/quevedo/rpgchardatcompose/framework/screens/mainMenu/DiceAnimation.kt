package ies.quevedo.rpgchardatcompose.framework.screens.mainMenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import ies.quevedo.rpgchardatcompose.R

@Composable
fun DiceAnimation() {
    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.dice))
    val progress by animateLottieCompositionAsState(
        composition = compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 0.5f
    )
    LottieAnimation(
        composition = compositionResult.value,
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .width(300.dp)
    )
}