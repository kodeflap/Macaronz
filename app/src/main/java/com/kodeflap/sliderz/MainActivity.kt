package com.kodeflap.sliderz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeflap.sliderz.ui.theme.Purple40
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
                    CircularProgressBar(
                        modifier = Modifier
                            .size(250.dp)
                            .background(Color.Black),
                        radius = 250f,
                        minValue = 0,
                        maxValue = 100,
                        gapBetweenOuterLineAndInnerCircle = 15f,
                        ProgressSweepColor = Brush.linearGradient(
                            listOf(
                                PurpleGrey80,
                                Color.Transparent
                            )
                        ),
                        innerCircleStrokeColor = Purple40,
                        innerCircleBackgroundColor = Brush.radialGradient(
                            listOf(
                                PurpleGrey80.copy(0.4f),
                                Purple80.copy(0.20f)
                            )
                        ),
                        outerLineColor = Brush.linearGradient(
                            listOf(
                                Purple80.copy(0.3f),
                                Purple40.copy(0.3f)
                            )
                        ),
                        progressTextColor = PurpleGrey80,
                        progressTextSize = 38.sp,
                        onPositionChange = {

                        }
                    )
                }
            }
        }
    }
}
