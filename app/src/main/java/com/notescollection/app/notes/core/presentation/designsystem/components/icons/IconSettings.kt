package com.notescollection.app.notes.core.presentation.designsystem.components.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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

val Settings: ImageVector
    get() {
        if (_settings != null) {
            return _settings!!
        }
        _settings =
            Builder(
                    name = "Settings",
                    defaultWidth = 20.0.dp,
                    defaultHeight = 20.0.dp,
                    viewportWidth = 20.0f,
                    viewportHeight = 20.0f,
                )
                .apply {
                    group {
                        path(
                            fill = SolidColor(Color(0x00000000)),
                            stroke = SolidColor(Color(0xFF1B1B1C)),
                            strokeLineWidth = 1.5f,
                            strokeLineCap = strokeCapRound,
                            strokeLineJoin = strokeJoinRound,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(10.0f, 12.5f)
                            curveTo(11.381f, 12.5f, 12.5f, 11.381f, 12.5f, 10.0f)
                            curveTo(12.5f, 8.62f, 11.381f, 7.5f, 10.0f, 7.5f)
                            curveTo(8.619f, 7.5f, 7.5f, 8.62f, 7.5f, 10.0f)
                            curveTo(7.5f, 11.381f, 8.619f, 12.5f, 10.0f, 12.5f)
                            close()
                        }
                        path(
                            fill = SolidColor(Color(0x00000000)),
                            stroke = SolidColor(Color(0xFF1B1B1C)),
                            strokeLineWidth = 1.5f,
                            strokeLineCap = strokeCapRound,
                            strokeLineJoin = strokeJoinRound,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(15.606f, 12.273f)
                            curveTo(15.505f, 12.502f, 15.475f, 12.755f, 15.52f, 13.001f)
                            curveTo(15.564f, 13.247f, 15.682f, 13.473f, 15.856f, 13.652f)
                            lineTo(15.902f, 13.697f)
                            curveTo(16.042f, 13.838f, 16.154f, 14.005f, 16.23f, 14.189f)
                            curveTo(16.307f, 14.373f, 16.346f, 14.57f, 16.346f, 14.769f)
                            curveTo(16.346f, 14.968f, 16.307f, 15.165f, 16.23f, 15.349f)
                            curveTo(16.154f, 15.533f, 16.042f, 15.7f, 15.902f, 15.841f)
                            curveTo(15.761f, 15.982f, 15.594f, 16.094f, 15.41f, 16.17f)
                            curveTo(15.226f, 16.246f, 15.029f, 16.286f, 14.83f, 16.286f)
                            curveTo(14.63f, 16.286f, 14.433f, 16.246f, 14.249f, 16.17f)
                            curveTo(14.066f, 16.094f, 13.898f, 15.982f, 13.758f, 15.841f)
                            lineTo(13.712f, 15.796f)
                            curveTo(13.534f, 15.621f, 13.307f, 15.504f, 13.061f, 15.459f)
                            curveTo(12.815f, 15.415f, 12.562f, 15.445f, 12.333f, 15.546f)
                            curveTo(12.109f, 15.642f, 11.918f, 15.801f, 11.784f, 16.004f)
                            curveTo(11.649f, 16.208f, 11.577f, 16.446f, 11.576f, 16.69f)
                            verticalLineTo(16.819f)
                            curveTo(11.576f, 17.22f, 11.416f, 17.606f, 11.132f, 17.89f)
                            curveTo(10.848f, 18.174f, 10.462f, 18.334f, 10.061f, 18.334f)
                            curveTo(9.659f, 18.334f, 9.273f, 18.174f, 8.989f, 17.89f)
                            curveTo(8.705f, 17.606f, 8.546f, 17.22f, 8.546f, 16.819f)
                            verticalLineTo(16.75f)
                            curveTo(8.54f, 16.5f, 8.459f, 16.256f, 8.313f, 16.052f)
                            curveTo(8.167f, 15.848f, 7.963f, 15.693f, 7.727f, 15.606f)
                            curveTo(7.499f, 15.505f, 7.245f, 15.476f, 7.0f, 15.52f)
                            curveTo(6.754f, 15.565f, 6.527f, 15.682f, 6.349f, 15.856f)
                            lineTo(6.303f, 15.902f)
                            curveTo(6.162f, 16.043f, 5.995f, 16.154f, 5.811f, 16.231f)
                            curveTo(5.627f, 16.307f, 5.43f, 16.346f, 5.231f, 16.346f)
                            curveTo(5.032f, 16.346f, 4.835f, 16.307f, 4.651f, 16.231f)
                            curveTo(4.467f, 16.154f, 4.3f, 16.043f, 4.159f, 15.902f)
                            curveTo(4.018f, 15.761f, 3.907f, 15.594f, 3.83f, 15.41f)
                            curveTo(3.754f, 15.226f, 3.715f, 15.029f, 3.715f, 14.83f)
                            curveTo(3.715f, 14.631f, 3.754f, 14.434f, 3.83f, 14.25f)
                            curveTo(3.907f, 14.066f, 4.018f, 13.899f, 4.159f, 13.758f)
                            lineTo(4.205f, 13.712f)
                            curveTo(4.379f, 13.534f, 4.496f, 13.307f, 4.541f, 13.061f)
                            curveTo(4.586f, 12.816f, 4.555f, 12.562f, 4.455f, 12.334f)
                            curveTo(4.359f, 12.11f, 4.199f, 11.918f, 3.996f, 11.784f)
                            curveTo(3.793f, 11.649f, 3.554f, 11.577f, 3.311f, 11.576f)
                            horizontalLineTo(3.182f)
                            curveTo(2.78f, 11.576f, 2.395f, 11.417f, 2.111f, 11.132f)
                            curveTo(1.826f, 10.848f, 1.667f, 10.463f, 1.667f, 10.061f)
                            curveTo(1.667f, 9.659f, 1.826f, 9.274f, 2.111f, 8.99f)
                            curveTo(2.395f, 8.705f, 2.78f, 8.546f, 3.182f, 8.546f)
                            horizontalLineTo(3.25f)
                            curveTo(3.501f, 8.54f, 3.744f, 8.459f, 3.948f, 8.313f)
                            curveTo(4.152f, 8.167f, 4.307f, 7.963f, 4.394f, 7.728f)
                            curveTo(4.495f, 7.499f, 4.525f, 7.246f, 4.48f, 7.0f)
                            curveTo(4.436f, 6.754f, 4.319f, 6.527f, 4.144f, 6.349f)
                            lineTo(4.099f, 6.303f)
                            curveTo(3.958f, 6.163f, 3.846f, 5.996f, 3.77f, 5.812f)
                            curveTo(3.693f, 5.628f, 3.654f, 5.431f, 3.654f, 5.231f)
                            curveTo(3.654f, 5.032f, 3.693f, 4.835f, 3.77f, 4.651f)
                            curveTo(3.846f, 4.467f, 3.958f, 4.3f, 4.099f, 4.159f)
                            curveTo(4.239f, 4.019f, 4.406f, 3.907f, 4.59f, 3.831f)
                            curveTo(4.774f, 3.754f, 4.971f, 3.715f, 5.171f, 3.715f)
                            curveTo(5.37f, 3.715f, 5.567f, 3.754f, 5.751f, 3.831f)
                            curveTo(5.935f, 3.907f, 6.102f, 4.019f, 6.243f, 4.159f)
                            lineTo(6.288f, 4.205f)
                            curveTo(6.467f, 4.38f, 6.693f, 4.497f, 6.939f, 4.541f)
                            curveTo(7.185f, 4.586f, 7.438f, 4.556f, 7.667f, 4.455f)
                            horizontalLineTo(7.727f)
                            curveTo(7.951f, 4.359f, 8.143f, 4.199f, 8.277f, 3.996f)
                            curveTo(8.412f, 3.793f, 8.484f, 3.555f, 8.485f, 3.311f)
                            verticalLineTo(3.182f)
                            curveTo(8.485f, 2.78f, 8.645f, 2.395f, 8.929f, 2.111f)
                            curveTo(9.213f, 1.827f, 9.598f, 1.667f, 10.0f, 1.667f)
                            curveTo(10.402f, 1.667f, 10.787f, 1.827f, 11.071f, 2.111f)
                            curveTo(11.356f, 2.395f, 11.515f, 2.78f, 11.515f, 3.182f)
                            verticalLineTo(3.25f)
                            curveTo(11.516f, 3.494f, 11.588f, 3.732f, 11.723f, 3.936f)
                            curveTo(11.858f, 4.139f, 12.049f, 4.298f, 12.273f, 4.394f)
                            curveTo(12.501f, 4.495f, 12.755f, 4.525f, 13.0f, 4.481f)
                            curveTo(13.246f, 4.436f, 13.473f, 4.319f, 13.652f, 4.144f)
                            lineTo(13.697f, 4.099f)
                            curveTo(13.838f, 3.958f, 14.005f, 3.846f, 14.189f, 3.77f)
                            curveTo(14.373f, 3.694f, 14.57f, 3.654f, 14.769f, 3.654f)
                            curveTo(14.968f, 3.654f, 15.165f, 3.694f, 15.349f, 3.77f)
                            curveTo(15.533f, 3.846f, 15.7f, 3.958f, 15.841f, 4.099f)
                            curveTo(15.982f, 4.24f, 16.094f, 4.407f, 16.17f, 4.591f)
                            curveTo(16.246f, 4.774f, 16.285f, 4.972f, 16.285f, 5.171f)
                            curveTo(16.285f, 5.37f, 16.246f, 5.567f, 16.17f, 5.751f)
                            curveTo(16.094f, 5.935f, 15.982f, 6.102f, 15.841f, 6.243f)
                            lineTo(15.795f, 6.288f)
                            curveTo(15.621f, 6.467f, 15.504f, 6.694f, 15.459f, 6.939f)
                            curveTo(15.415f, 7.185f, 15.445f, 7.438f, 15.545f, 7.667f)
                            verticalLineTo(7.728f)
                            curveTo(15.642f, 7.952f, 15.801f, 8.143f, 16.004f, 8.277f)
                            curveTo(16.208f, 8.412f, 16.446f, 8.484f, 16.69f, 8.485f)
                            horizontalLineTo(16.818f)
                            curveTo(17.22f, 8.485f, 17.605f, 8.645f, 17.89f, 8.929f)
                            curveTo(18.174f, 9.213f, 18.333f, 9.598f, 18.333f, 10.0f)
                            curveTo(18.333f, 10.402f, 18.174f, 10.788f, 17.89f, 11.072f)
                            curveTo(17.605f, 11.356f, 17.22f, 11.516f, 16.818f, 11.516f)
                            horizontalLineTo(16.75f)
                            curveTo(16.506f, 11.516f, 16.268f, 11.589f, 16.065f, 11.723f)
                            curveTo(15.862f, 11.858f, 15.702f, 12.049f, 15.606f, 12.273f)
                            close()
                        }
                    }
                }
                .build()
        return _settings!!
    }

private var _settings: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Settings, contentDescription = null)
    }
}
