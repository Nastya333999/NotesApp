package com.notescollection.app.core.presentation.designsystem.components.icons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
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

val IconInvisible: ImageVector
    get() {
        if (_eyeOff != null) {
            return _eyeOff!!
        }
        _eyeOff =
            Builder(
                    name = "EyeOff",
                    defaultWidth = 24.0.dp,
                    defaultHeight = 24.0.dp,
                    viewportWidth = 20.0f,
                    viewportHeight = 20.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 1.5f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(8.952f, 4.244f)
                        curveTo(9.291f, 4.194f, 9.641f, 4.167f, 10.0f, 4.167f)
                        curveTo(14.255f, 4.167f, 17.046f, 7.921f, 17.984f, 9.406f)
                        curveTo(18.097f, 9.585f, 18.154f, 9.675f, 18.186f, 9.814f)
                        curveTo(18.21f, 9.918f, 18.21f, 10.082f, 18.186f, 10.186f)
                        curveTo(18.154f, 10.325f, 18.097f, 10.415f, 17.983f, 10.596f)
                        curveTo(17.733f, 10.992f, 17.352f, 11.548f, 16.847f, 12.15f)
                        moveTo(5.604f, 5.596f)
                        curveTo(3.802f, 6.818f, 2.579f, 8.516f, 2.018f, 9.404f)
                        curveTo(1.904f, 9.585f, 1.847f, 9.675f, 1.815f, 9.814f)
                        curveTo(1.791f, 9.918f, 1.791f, 10.082f, 1.815f, 10.186f)
                        curveTo(1.847f, 10.325f, 1.903f, 10.415f, 2.017f, 10.594f)
                        curveTo(2.955f, 12.079f, 5.746f, 15.833f, 10.0f, 15.833f)
                        curveTo(11.716f, 15.833f, 13.193f, 15.223f, 14.407f, 14.397f)
                        moveTo(2.5f, 2.5f)
                        lineTo(17.5f, 17.5f)
                        moveTo(8.233f, 8.232f)
                        curveTo(7.78f, 8.685f, 7.5f, 9.31f, 7.5f, 10.0f)
                        curveTo(7.5f, 11.381f, 8.62f, 12.5f, 10.0f, 12.5f)
                        curveTo(10.691f, 12.5f, 11.316f, 12.22f, 11.768f, 11.768f)
                    }
                }
                .build()
        return _eyeOff!!
    }

private var _eyeOff: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Icon(imageVector = IconInvisible, contentDescription = null)
    }
}
