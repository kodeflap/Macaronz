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

package com.kodeflap.sliderz.stripe

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
public fun StripeStyle(
  stripeColor: Color,
  stripeThickness: Dp,
  stripeBackground: Color,
  stripeShadowColor: Color,
): Brush {
  val stripeWidth = with(LocalDensity.current) {
    stripeThickness.toPx()
  }
  val brushSize = stripeWidth * 2
  val stripeStart = stripeWidth / brushSize
  return Brush.linearGradient(
    stripeStart to stripeBackground,
    stripeStart to stripeColor,
    start = Offset(0f, 0f),
    end = Offset(brushSize, brushSize),
    tileMode = TileMode.Repeated
  )
}
