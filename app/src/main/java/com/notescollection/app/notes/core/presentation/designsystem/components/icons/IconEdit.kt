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

val IconEdit: ImageVector
    get() {
        if (_IconEdit != null) {
            return _IconEdit!!
        }
        _IconEdit =
            Builder(
                    name = "Edit03",
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
                        moveTo(10.5f, 16.666f)
                        horizontalLineTo(18.0f)
                        moveTo(3.0f, 16.666f)
                        horizontalLineTo(4.395f)
                        curveTo(4.803f, 16.666f, 5.007f, 16.666f, 5.199f, 16.62f)
                        curveTo(5.369f, 16.579f, 5.531f, 16.512f, 5.681f, 16.421f)
                        curveTo(5.849f, 16.317f, 5.993f, 16.173f, 6.281f, 15.885f)
                        lineTo(16.75f, 5.416f)
                        curveTo(17.44f, 4.726f, 17.44f, 3.607f, 16.75f, 2.916f)
                        curveTo(16.06f, 2.226f, 14.94f, 2.226f, 14.25f, 2.916f)
                        lineTo(3.781f, 13.385f)
                        curveTo(3.493f, 13.673f, 3.349f, 13.818f, 3.246f, 13.986f)
                        curveTo(3.154f, 14.135f, 3.087f, 14.297f, 3.046f, 14.467f)
                        curveTo(3.0f, 14.659f, 3.0f, 14.863f, 3.0f, 15.271f)
                        verticalLineTo(16.666f)
                        close()
                    }
                }
                .build()
        return _IconEdit!!
    }

private var _IconEdit: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = IconEdit, contentDescription = null)
    }
}
