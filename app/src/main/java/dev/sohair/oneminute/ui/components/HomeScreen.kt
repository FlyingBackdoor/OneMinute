package dev.sohair.oneminute.ui.components

import NotificationUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.sohair.oneminute.R
import dev.sohair.oneminute.data.TimerState
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: HomeScreenViewModel = getViewModel()
    var elapsedTime by remember { mutableStateOf(0L) }
    var timerState by remember { mutableStateOf(TimerState.PAUSED) }
    var timerText by remember { mutableStateOf("0:00") }
    val context = LocalContext.current

    LaunchedEffect(viewModel.timerState) {
        viewModel.timerState.collect { state ->
            timerState = state
        }
    }

    LaunchedEffect(viewModel.currentTime) {
        viewModel.currentTime.collect { time ->
            elapsedTime = time
            val minutes = time / 60
            val seconds = time % 60
            timerText = "$minutes:${if (seconds < 10) "0$seconds" else seconds}"
            if (time == 0L){
                NotificationUtils.showNotification(context, "Finished...")
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        TimerUi(text = timerText, filledPercentage = calculatePercentage(elapsedTime))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                viewModel.stopTimer()
                elapsedTime = 0L
                timerText = "0:00"
            }) {
                Text(text = stringResource(id = R.string.stop))
            }

            Button(onClick = {
                viewModel.startPauseTimer()
            }) {
                Text(
                    text = if (timerState == TimerState.PAUSED) stringResource(id = R.string.start)
                    else stringResource(id = R.string.pause)
                )
            }
        }
    }
}

private fun calculatePercentage(remainingTime: Long): Float {
    val totalTime = 60L
    return ((remainingTime.toFloat() / totalTime.toFloat()))
}