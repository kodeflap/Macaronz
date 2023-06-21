/*
 * Copyright (c) $[today] [name of copyright owner]
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
package com.kodeflap.Macaronz

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kodeflap.Macaronz.stripe.StripeStyle

@Composable
public fun LinearProgress(
  modifier: Modifier = Modifier,
  value: Float,
  animationDuration: Int = 1000,
  animationDelay: Int = 0,
  backgroundColor: Color = Color.Cyan,
  minValue: Int = 0,
  maxValue: Int = 100
) {
  var data by remember {
    mutableStateOf(-1000f)
  }

  val progressAngle by animateFloatAsState(
    targetValue = data,
    animationSpec = tween(
      durationMillis = animationDuration,
      delayMillis = animationDelay
    )
  )

  LaunchedEffect(Unit) {
    data = value
  }

  val angle = progressAngle * 360f / (maxValue - minValue).toFloat()

  Box(modifier = modifier) {
    Canvas(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      val width = size.width
      val height = size.height

      drawRoundRect(
        color = backgroundColor
      )
      drawRoundRect(
        brush = StripeStyle(stripeColor = Color.Blue, stripeThickness = 60.dp, stripeBackground = Color.Yellow),
        size = Size(angle * width, height)
      )
    }
  }
}
