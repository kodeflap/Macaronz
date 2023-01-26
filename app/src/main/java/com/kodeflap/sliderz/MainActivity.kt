/*
 * Copyright [2023] [kodeflap]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
