package com.kodeflap.sliderz

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlin.math.*

/**
 * Circular progress bar
 *
 * @param modifier
 * @param radius
 * @param minValue
 * @param maxValue
 * @param gapBetweenOuterLineAndInnerCircle
 * @param ProgressSweepColor
 * @param innerCircleStrokeColor
 * @param innerCircleBackgroundColor
 * @param outerLineColor
 * @param progressTextColor
 * @param progressTextSize
 * @param onPositionChange
 * @receiver
 */

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    radius: Float,
    minValue: Int,
    maxValue: Int,
    gapBetweenOuterLineAndInnerCircle: Float,
    ProgressSweepColor: Brush,
    innerCircleStrokeColor: Color,
    innerCircleBackgroundColor: Brush,
    outerLineColor: Brush,
    progressTextColor: Color,
    progressTextSize: TextUnit = TextUnit.Unspecified,
    onPositionChange: (Int) -> Unit
) {
    var center by remember {
        mutableStateOf(Offset.Zero)
    }
    var position by remember {
        mutableStateOf(minValue)
    }
    var anglePosition by remember {
        mutableStateOf(0f)
    }
    var dragStartAngle by remember {
        mutableStateOf(0f)
    }
    var oldPositionValue by remember {
        mutableStateOf(minValue)
    }

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {

                    /** Drag Gesture
                     * The Drag gesture specifies the starting point and ending point
                     *
                     */

                    detectDragGestures(
                        onDragStart = { offset ->
                            dragStartAngle = -atan2(
                                x = center.y - offset.y,
                                y = center.x - offset.x
                            ) * (180f / PI).toFloat()
                            dragStartAngle = (dragStartAngle + 180f).mod(360f)
                        },
                        onDrag = { change, _ ->
                            var touchAngle = -atan2(
                                x = center.y - change.position.y,
                                y = center.x - change.position.x
                            ) * (180f / PI).toFloat()

                            touchAngle = (touchAngle + 180f).mod(360f)

                            val currentAngle = oldPositionValue * 360f / (maxValue - minValue)
                            anglePosition = touchAngle - currentAngle

                            val lowerValue = currentAngle - (360f / (maxValue - minValue) * 5)
                            val highValue = currentAngle + (360f / (maxValue - minValue) * 5)

                            if (dragStartAngle in lowerValue..highValue) {
                                position =
                                    (oldPositionValue + (anglePosition / (360f / (maxValue - minValue))).roundToInt())
                            }
                        },
                        onDragEnd = {
                            oldPositionValue = position
                            onPositionChange(position)
                        }
                    )
                }
        ) {
            val width = size.width
            val height = size.height
            val thickness = width / 25f
            center = Offset(x = width / 2f, y = height / 2f)

            /** --------inner circle----------------
             *
             */
            drawCircle(
                brush = innerCircleBackgroundColor,
                radius = radius,
                center = center
            )

            drawCircle(
                style = Stroke(width = thickness),
                color = innerCircleStrokeColor,
                radius = radius,
                center = center
            )

            /** Progress Stroke color specification
             *
             */
            drawArc(
                brush = ProgressSweepColor,
                startAngle = 90f,
                sweepAngle = (360f / maxValue) * position.toFloat(),
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
                )
            )

            val outerRadius = radius + thickness / 2f
            for (i in 0..(maxValue - minValue)) {
                val color =
                    if (i < position - minValue) ProgressSweepColor else outerLineColor
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
                        brush = color,
                        start = start,
                        end = end,
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    drawText(
                        "$position %",
                        center.x,
                        center.y + 45.dp.toPx() / 3f,
                        Paint().apply {
                            textSize = progressTextSize.toPx()
                            textAlign = Paint.Align.CENTER
                            color = progressTextColor.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }
}
