package com.notescollection.app.core.presentation.designsystem.components.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val LogoN: ImageVector
    get() {
        if (_LogoN != null) {
            return _LogoN!!
        }
        _LogoN =
            Builder(
                    name = "_Logo",
                    defaultWidth = 84.0.dp,
                    defaultHeight = 111.0.dp,
                    viewportWidth = 84.0f,
                    viewportHeight = 111.0f,
                )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFFffffff)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(0.0f, 110.14f)
                        verticalLineTo(0.06f)
                        horizontalLineTo(36.17f)
                        lineTo(62.12f, 97.41f)
                        horizontalLineTo(64.64f)
                        verticalLineTo(0.06f)
                        horizontalLineTo(83.35f)
                        verticalLineTo(110.14f)
                        horizontalLineTo(47.34f)
                        lineTo(21.39f, 12.79f)
                        horizontalLineTo(18.71f)
                        verticalLineTo(40.24f)
                        verticalLineTo(45.01f)
                        verticalLineTo(62.4f)
                        verticalLineTo(70.83f)
                        verticalLineTo(90.23f)
                        verticalLineTo(110.14f)
                        horizontalLineTo(0.0f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFFffffff)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(18.71f, 110.14f)
                        verticalLineTo(90.23f)
                        horizontalLineTo(32.26f)
                        lineTo(43.5f, 110.14f)
                        horizontalLineTo(18.71f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFFffffff)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(18.71f, 70.83f)
                        horizontalLineTo(27.09f)
                        lineTo(18.71f, 62.4f)
                        verticalLineTo(70.83f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFFffffff)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(18.71f, 45.01f)
                        horizontalLineTo(23.52f)
                        lineTo(18.71f, 40.24f)
                        verticalLineTo(45.01f)
                        close()
                    }
                }
                .build()
        return _LogoN!!
    }

private var _LogoN: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = LogoN, contentDescription = null)
    }
}
