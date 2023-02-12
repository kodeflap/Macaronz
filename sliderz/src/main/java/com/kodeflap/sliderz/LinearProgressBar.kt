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
package com.kodeflap.sliderz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.kodeflap.sliderz.stripe.StripeStyle

@Composable
public fun LinearProgressBar(
  modifier: Modifier = Modifier,
  value: Float,
  animationColor: List<Color>,
  borderWidth: Float,
  borderColor: Color = Color.Cyan,
  borderRadius: Float,
  shape: Shape = RoundedCornerShape(16.dp)
) {
  Column(
    modifier = modifier
      .clip(shape)
      .background(borderColor)
      .fillMaxHeight()
  ) {
    Box(
      modifier = modifier
        .clip(shape)
        .size(width = 30.dp, height = 1000.dp)
        .background(
          StripeStyle(
            stripeColor = Color.Blue,
            stripeThickness = 6.dp,
            stripeBackground = Color.Magenta,
            stripeShadowColor = Color.Blue
          )
        )
    )
  }
}
