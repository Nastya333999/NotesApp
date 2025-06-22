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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val IconCopy: ImageVector
    get() {
        if (_copy != null) {
            return _copy!!
        }
        _copy =
            Builder(
                name = "Copy",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 20.0f,
                viewportHeight = 20.0f,
            )
                .apply {
                    group {
                        path(
                            fill = SolidColor(Color(0x00000000)),
                            stroke = SolidColor(Color(0xFF000000)),
                            strokeLineWidth = 1.5f,
                            strokeLineCap = strokeCapRound,
                            strokeLineJoin = strokeJoinRound,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(4.167f, 12.5f)
                            curveTo(3.39f, 12.5f, 3.002f, 12.5f, 2.696f, 12.373f)
                            curveTo(2.287f, 12.204f, 1.963f, 11.88f, 1.794f, 11.471f)
                            curveTo(1.667f, 11.165f, 1.667f, 10.777f, 1.667f, 10.0f)
                            verticalLineTo(4.333f)
                            curveTo(1.667f, 3.4f, 1.667f, 2.933f, 1.848f, 2.577f)
                            curveTo(2.008f, 2.263f, 2.263f, 2.008f, 2.577f, 1.848f)
                            curveTo(2.933f, 1.667f, 3.4f, 1.667f, 4.333f, 1.667f)
                            horizontalLineTo(10.0f)
                            curveTo(10.777f, 1.667f, 11.165f, 1.667f, 11.471f, 1.794f)
                            curveTo(11.88f, 1.963f, 12.204f, 2.287f, 12.373f, 2.696f)
                            curveTo(12.5f, 3.002f, 12.5f, 3.39f, 12.5f, 4.167f)
                            moveTo(10.167f, 18.333f)
                            horizontalLineTo(15.667f)
                            curveTo(16.6f, 18.333f, 17.067f, 18.333f, 17.423f, 18.152f)
                            curveTo(17.737f, 17.992f, 17.992f, 17.737f, 18.152f, 17.423f)
                            curveTo(18.333f, 17.067f, 18.333f, 16.6f, 18.333f, 15.667f)
                            verticalLineTo(10.167f)
                            curveTo(18.333f, 9.233f, 18.333f, 8.767f, 18.152f, 8.41f)
                            curveTo(17.992f, 8.096f, 17.737f, 7.841f, 17.423f, 7.682f)
                            curveTo(17.067f, 7.5f, 16.6f, 7.5f, 15.667f, 7.5f)
                            horizontalLineTo(10.167f)
                            curveTo(9.233f, 7.5f, 8.767f, 7.5f, 8.41f, 7.682f)
                            curveTo(8.096f, 7.841f, 7.841f, 8.096f, 7.682f, 8.41f)
                            curveTo(7.5f, 8.767f, 7.5f, 9.233f, 7.5f, 10.167f)
                            verticalLineTo(15.667f)
                            curveTo(7.5f, 16.6f, 7.5f, 17.067f, 7.682f, 17.423f)
                            curveTo(7.841f, 17.737f, 8.096f, 17.992f, 8.41f, 18.152f)
                            curveTo(8.767f, 18.333f, 9.233f, 18.333f, 10.167f, 18.333f)
                            close()
                        }
                    }
                }
                .build()
        return _copy!!
    }

private var _copy: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Icon(imageVector = IconCopy, contentDescription = null)
    }
}
