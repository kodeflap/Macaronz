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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kodeflap.sliderz.ui.theme.Purple40
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
                        initialValue = 10,
                        primaryColor = PurpleGrey80,
                        secondaryColor = Purple40,
                        onPositionChange = {

                        }
                    )
                }
            }
        }
    }
}
