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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    radius: Float,
    initialValue: Int,
    minValue: Int = 0,
    maxValue: Int = 100,
    primaryColor: Color,
    secondaryColor: Color,
    onPositionChange: (Int) -> Unit
) {
    var center by remember {
        mutableStateOf(Offset.Zero)
    }
    var position by remember {
        mutableStateOf(initialValue)
    }
    var anglePosition by remember {
        mutableStateOf(0f)
    }
    var angleStartPosition by remember {
        mutableStateOf(0f)
    }
    var centerValue by remember {
        mutableStateOf(initialValue)
    }

    Box(modifier) {
        centerValue = initialValue
        position = initialValue
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            angleStartPosition = -atan2(
                                x = center.y - offset.y,
                                y = center.x - offset.x
                            ) * (180f / PI).toFloat()
                            angleStartPosition = (angleStartPosition + 180f).mod(360f)
                        },
                        onDragEnd = {
                            centerValue = position
                            onPositionChange(position)
                        }
                    ) { change, _ ->
                        var touchAngle = -atan2(
                            x = center.y - change.position.y,
                            y = center.x - change.position.x
                        ) * (180f / PI).toFloat()
                        touchAngle = (touchAngle + 180f).mod(360f)
                        anglePosition = touchAngle - centerValue * 360f / maxValue

                        if (angleStartPosition in
                            (centerValue.toFloat() * 360 / maxValue - (360 / maxValue * 5))
                            ..
                            (centerValue.toFloat() * 360 / maxValue + (360 / maxValue * 5))
                        ) {
                            val dragFrom100 = (position == maxValue &&
                                    (centerValue + (anglePosition / (360f / maxValue.toFloat()))).roundToInt() in (minValue..maxValue - 5))
                            val dragFrom0 = (position == minValue &&
                                    (centerValue + (anglePosition / (360f / maxValue.toFloat()))).roundToInt() in (minValue + 5..maxValue))
                            if (!dragFrom0 && !dragFrom100) {
                                position =
                                    (centerValue + (anglePosition / (360f / maxValue.toFloat()))).roundToInt()
                            }
                        }
                    }
                }
        ) {
            val width = size.width
            val height = size.height
            val thickness = width / 25f
            center = Offset(x = width / 2f, y = height / 2f)

            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        primaryColor.copy(0.4f),
                        secondaryColor.copy(0.20f)
                    )
                ),
                radius = radius,
                center = center
            )

            drawCircle(
                style = Stroke(width = thickness),
                color = secondaryColor,
                radius = radius,
                center = center
            )

            drawArc(
                color = primaryColor,
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
            val gap = 15f
            for (i in 0..(maxValue - minValue)) {
                val color =
                    if (i < position - minValue) primaryColor else primaryColor.copy(alpha = 0.3f)
                val angleInDegree = i * 360f / (maxValue - minValue).toFloat()
                val angleInRad = angleInDegree * PI / 180f + PI / 2f
                val yGap = cos(angleInDegree * PI / 180f) * gap
                val xGap = -sin(angleInDegree * PI / 180f) * gap

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
                        color = color,
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
                            textSize = 38.sp.toPx()
                            textAlign = Paint.Align.CENTER
                            color = primaryColor.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }
}
