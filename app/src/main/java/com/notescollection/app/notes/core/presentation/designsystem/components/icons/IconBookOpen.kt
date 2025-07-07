package com.notescollection.app.notes.core.presentation.designsystem.components.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val IconBookOpen: ImageVector
    get() {
        if (_IconBookOpen != null) {
            return _IconBookOpen!!
        }
        _IconBookOpen =
            Builder(
                    name = "BookOpen01",
                    defaultWidth = 21.0.dp,
                    defaultHeight = 20.0.dp,
                    viewportWidth = 21.0f,
                    viewportHeight = 20.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF1B1B1C)),
                        strokeLineWidth = 1.5f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(10.5f, 17.5f)
                        lineTo(10.417f, 17.375f)
                        curveTo(9.838f, 16.507f, 9.548f, 16.073f, 9.166f, 15.758f)
                        curveTo(8.827f, 15.48f, 8.437f, 15.271f, 8.018f, 15.144f)
                        curveTo(7.544f, 15.0f, 7.023f, 15.0f, 5.979f, 15.0f)
                        horizontalLineTo(4.833f)
                        curveTo(3.9f, 15.0f, 3.433f, 15.0f, 3.077f, 14.818f)
                        curveTo(2.763f, 14.659f, 2.508f, 14.404f, 2.348f, 14.09f)
                        curveTo(2.167f, 13.733f, 2.167f, 13.267f, 2.167f, 12.333f)
                        verticalLineTo(5.167f)
                        curveTo(2.167f, 4.233f, 2.167f, 3.767f, 2.348f, 3.41f)
                        curveTo(2.508f, 3.096f, 2.763f, 2.841f, 3.077f, 2.682f)
                        curveTo(3.433f, 2.5f, 3.9f, 2.5f, 4.833f, 2.5f)
                        horizontalLineTo(5.167f)
                        curveTo(7.034f, 2.5f, 7.967f, 2.5f, 8.68f, 2.863f)
                        curveTo(9.307f, 3.183f, 9.817f, 3.693f, 10.137f, 4.32f)
                        curveTo(10.5f, 5.033f, 10.5f, 5.966f, 10.5f, 7.833f)
                        moveTo(10.5f, 17.5f)
                        verticalLineTo(7.833f)
                        moveTo(10.5f, 17.5f)
                        lineTo(10.583f, 17.375f)
                        curveTo(11.162f, 16.507f, 11.452f, 16.073f, 11.834f, 15.758f)
                        curveTo(12.173f, 15.48f, 12.563f, 15.271f, 12.982f, 15.144f)
                        curveTo(13.456f, 15.0f, 13.977f, 15.0f, 15.021f, 15.0f)
                        horizontalLineTo(16.167f)
                        curveTo(17.1f, 15.0f, 17.567f, 15.0f, 17.923f, 14.818f)
                        curveTo(18.237f, 14.659f, 18.492f, 14.404f, 18.652f, 14.09f)
                        curveTo(18.833f, 13.733f, 18.833f, 13.267f, 18.833f, 12.333f)
                        verticalLineTo(5.167f)
                        curveTo(18.833f, 4.233f, 18.833f, 3.767f, 18.652f, 3.41f)
                        curveTo(18.492f, 3.096f, 18.237f, 2.841f, 17.923f, 2.682f)
                        curveTo(17.567f, 2.5f, 17.1f, 2.5f, 16.167f, 2.5f)
                        horizontalLineTo(15.833f)
                        curveTo(13.967f, 2.5f, 13.033f, 2.5f, 12.32f, 2.863f)
                        curveTo(11.693f, 3.183f, 11.183f, 3.693f, 10.863f, 4.32f)
                        curveTo(10.5f, 5.033f, 10.5f, 5.966f, 10.5f, 7.833f)
                    }
                }
                .build()
        return _IconBookOpen!!
    }

private var _IconBookOpen: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = IconBookOpen, contentDescription = null)
    }
}
