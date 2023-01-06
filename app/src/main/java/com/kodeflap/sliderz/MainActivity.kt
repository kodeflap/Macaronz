/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kodeflap.sliderz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeflap.sliderz.ui.theme.Purple80
import com.kodeflap.sliderz.ui.theme.PurpleGrey80
import com.kodeflap.sliderz.ui.theme.SliderzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SliderzTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProgressSamples()
                }
            }
        }
    }

    @Preview
    @Composable
    fun ProgressSamples() {

        val targetValue = 95

        //////////////////////////circle progress bar///////////////////////////
        CircularProgressBar(
            modifier = Modifier
                .size(250.dp),
            radius = 260f,
            number = targetValue.toFloat(),
            maxValue = 100,
            gapBetweenOuterLineAndInnerCircle = 15f,
            progressSweepColor =
            Brush.linearGradient(
                listOf(
                    Color.Blue,
                    Color.LightGray.copy(1f)
                )
            ),
            innerCircleStrokeColor = Color.DarkGray,
            innerCircleBackgroundColor = Brush.radialGradient(
                listOf(
                    PurpleGrey80.copy(0.4f),
                    Purple80.copy(0.20f)
                )
            ),
            outerLineColor = Brush.linearGradient(
                listOf(
                    Purple80.copy(0.3f),
                    Purple80.copy(0.1f)
                )
            ),
            centerMainTextColor = PurpleGrey80,
            centerMainTextSize = 70.sp,
            centerSubTextColor = Purple80,
            centerSubTextSize = 20.sp,
            showCenterText = true,
            showCenterSubText = true,
            centerTextMainContent = "$targetValue%",
            centerSubTextContent = "Completed",
        )
    }
}
