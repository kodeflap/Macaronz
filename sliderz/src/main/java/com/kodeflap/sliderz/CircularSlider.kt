package com.kodeflap.sliderz

import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Circular progress bar
 *
 * @param modifier
 * @param radius
 * @param number
 * @param minValue
 * @param maxValue
 * @param animationDuration
 * @param animationDelay
 * @param gapBetweenOuterLineAndInnerCircle
 * @param progressSweepColor
 * @param innerCircleStrokeColor
 * @param innerCircleBackgroundColor
 * @param outerLineColor
 * @param centerMainTextColor
 * @param centerMainTextSize
 * @param centerSubTextColor
 * @param centerSubTextSize
 * @param showCenterText
 * @param showCenterSubText
 * @param centerTextMainContent
 * @param centerSubTextContent
 */

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    radius: Float,
    number: Float,
    minValue: Int = 0,
    maxValue: Int,
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    gapBetweenOuterLineAndInnerCircle: Float,
    progressSweepColor: Brush,
    innerCircleStrokeColor: Color,
    innerCircleBackgroundColor: Brush,
    outerLineColor: Brush,
    centerMainTextColor: Color,
    centerMainTextSize: TextUnit = TextUnit.Unspecified,
    centerSubTextColor: Color,
    centerSubTextSize: TextUnit = TextUnit.Unspecified,
    showCenterText: Boolean,
    showCenterSubText: Boolean,
    centerTextMainContent: String,
    centerSubTextContent: String
) {
    var center by remember {
        mutableStateOf(Offset.Zero)
    }

    val centerValue by remember {
        mutableStateOf(number)
    }

    var innerCircleData by remember {
        mutableStateOf(-1f)
    }

    /** Animation for progress
     *
     *It defines the animation style for progress bar animation duration and delay
    Launched Effect used to start animation
     */
    val progressAngle = animateFloatAsState(
        targetValue = innerCircleData,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    LaunchedEffect(Unit) {
        innerCircleData = number
    }

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            val thickness = width / 25f
            center = Offset(x = width / 2f, y = height / 2f)

            /** --------inner circle----------------
             *It draws a inner circle you can customize according to the need
             */
            drawCircle(
                brush = innerCircleBackgroundColor,
                radius = radius,
                center = center
            )

            /** Second circle
             * The second outer circle
             */
            drawCircle(
                style = Stroke(width = thickness),
                color = innerCircleStrokeColor,
                radius = radius,
                center = center
            )

            /** Progress showing arc specification starts here
             * An arc to show the progress by calculating the sweep angle
             */
            drawArc(
                brush = progressSweepColor,
                startAngle = 90f,
                sweepAngle = (progressAngle.value / 100) * 360,
                style = Stroke(
                    width = thickness,
                    cap = StrokeCap.Round
                ),
                useCenter = false,
                size = Size(
                    width = radius * 2f,
                    height = radius * 2f
                ),
                topLeft = Offset(
                    (width - radius * 2f) / 2f,
                    (height - radius * 2f) / 2f
                ),
            )
            /** Outline circle code
             * val outerRadius is calculated
             * from 0 to (maxValue - minValue) it draws the lines by calculating
             * gap between x and y
             */
            val outerRadius = radius + thickness / 2f
            for (i in 0..(maxValue - minValue)) {
                val color =
                    if (i < centerValue - minValue) progressSweepColor else outerLineColor
                val angleInDegree = i * 360f / (maxValue - minValue).toFloat()
                val angleInRad = angleInDegree * PI / 180f + PI / 2f
                val yGap = cos(angleInDegree * PI / 180f) * gapBetweenOuterLineAndInnerCircle
                val xGap = -sin(angleInDegree * PI / 180f) * gapBetweenOuterLineAndInnerCircle

                val start = Offset(
                    x = (outerRadius * cos(angleInRad) + center.x + xGap).toFloat(),
                    y = (outerRadius * sin(angleInRad) + center.y + yGap).toFloat()
                )
                val end = Offset(
                    x = (outerRadius * cos(angleInRad) + center.x + xGap).toFloat(),
                    y = (outerRadius * sin(angleInRad) + thickness + center.y + yGap).toFloat()
                )
                rotate(
                    angleInDegree,
                    pivot = start
                ) {
                    drawLine(
                        brush = color as Brush,
                        start = start,
                        end = end,
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
            /** Main and sub text
             * Used to show main text and sub text in the center of the circle
             * Checks if the main content to shown or sub content or both
             */
            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    if (showCenterText) {
                        drawText(
                            centerTextMainContent,
                            center.x,
                            center.y + 45.dp.toPx() / 3f,
                            Paint().apply {
                                textSize = centerMainTextSize.toPx()
                                textAlign = Paint.Align.CENTER
                                color = centerMainTextColor.toArgb()
                                isFakeBoldText = true
                            }
                        )
                        if (showCenterSubText) {
                            drawText(
                                centerSubTextContent,
                                center.x,
                                center.y + 45.dp.toPx() / 1f,
                                Paint().apply {
                                    textSize = centerSubTextSize.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = centerSubTextColor.toArgb()
                                    isFakeBoldText = true
                                }
                            )
                        }
                    } else {

                    }

                }
            }
        }
    }
}



