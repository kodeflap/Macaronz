package com.kodeflap.sliderz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeflap.sliderz.ui.theme.PurpleGrey40
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



    @Preview(showBackground = true)
@Composable
fun ProgressSamples() {

    val targetValue = 95

    //////////////////////////circle progress bar///////////////////////////
    SliderzCircularProgressBar(
        modifier = Modifier
            .size(250.dp),
        radius = 260f,
        number = targetValue.toFloat(),
        innerCircleBackgroundColor = Brush.radialGradient(
            listOf(
                PurpleGrey80.copy(0.40f),
                PurpleGrey40.copy(0.30f)
            )
        ),
        centerSubTextColor = PurpleGrey80,
        centerTextMainContent = "$targetValue%",
        centerSubTextContent = "Completed"
    )
}
}
