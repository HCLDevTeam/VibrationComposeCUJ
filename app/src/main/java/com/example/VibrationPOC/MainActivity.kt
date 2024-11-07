package com.example.VibrationPOC

import android.content.Context
import android.os.Bundle
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.VibrationPOC.ui.theme.VibrationPOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VibrationPOCTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    VibrationExamplesScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun VibrationExamplesScreen() {
    Column {
        VibrationCookbook()
    }
}

@Preview
@Composable
fun VibrationCookbook() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        //Vibrate using LocalHapticFeedback
        val haptic = LocalHapticFeedback.current

        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }) {
            Text("Long Press Vibration")
        }
        Spacer(modifier = Modifier.height(16.dp)) // Adjust the spacing as needed

        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        }) {
            Text("Text Handle Move Vibration")
        }
        Spacer(modifier = Modifier.height(16.dp)) // Adjust the spacing as needed

        // Vibrate using Framework VibratorManager
        // Requires VIBRATE permission
        val context = LocalContext.current
        val vibrator =  // Requires Context to be passed
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

        val vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
        val combinedVibration = CombinedVibration.createParallel(vibrationEffect)

        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            vibrator.vibrate(combinedVibration)

        }) {
            Text("System Service Vibration")
        }

    }
}
