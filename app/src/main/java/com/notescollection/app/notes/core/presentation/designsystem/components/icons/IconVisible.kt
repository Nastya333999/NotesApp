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

val IconVisible: ImageVector
    get() {
        if (_eye != null) {
            return _eye!!
        }
        _eye =
            Builder(
                    name = "Eye",
                    defaultWidth = 20.0.dp,
                    defaultHeight = 20.0.dp,
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
                        moveTo(2.017f, 10.594f)
                        curveTo(1.903f, 10.415f, 1.847f, 10.325f, 1.815f, 10.186f)
                        curveTo(1.791f, 10.082f, 1.791f, 9.918f, 1.815f, 9.814f)
                        curveTo(1.847f, 9.675f, 1.903f, 9.585f, 2.017f, 9.406f)
                        curveTo(2.955f, 7.921f, 5.746f, 4.167f, 10.0f, 4.167f)
                        curveTo(14.255f, 4.167f, 17.046f, 7.921f, 17.984f, 9.406f)
                        curveTo(18.097f, 9.585f, 18.154f, 9.675f, 18.186f, 9.814f)
                        curveTo(18.21f, 9.918f, 18.21f, 10.082f, 18.186f, 10.186f)
                        curveTo(18.154f, 10.325f, 18.097f, 10.415f, 17.984f, 10.594f)
                        curveTo(17.046f, 12.079f, 14.255f, 15.833f, 10.0f, 15.833f)
                        curveTo(5.746f, 15.833f, 2.955f, 12.079f, 2.017f, 10.594f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 1.5f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(10.0f, 12.5f)
                        curveTo(11.381f, 12.5f, 12.5f, 11.381f, 12.5f, 10.0f)
                        curveTo(12.5f, 8.619f, 11.381f, 7.5f, 10.0f, 7.5f)
                        curveTo(8.62f, 7.5f, 7.5f, 8.619f, 7.5f, 10.0f)
                        curveTo(7.5f, 11.381f, 8.62f, 12.5f, 10.0f, 12.5f)
                        close()
                    }
                }
                .build()
        return _eye!!
    }

private var _eye: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Icon(imageVector = IconVisible, contentDescription = null)
    }
}
