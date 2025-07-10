package com.notescollection.app.notes.core.presentation.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.TextRange

@Stable
interface UiText {

    @Stable
    data class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf(),
    ) : UiText {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as StringResource

            if (id != other.id) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + args.contentHashCode()
            return result
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is StringResource -> stringResource(id, *args)
            else -> ""
        }
    }
}

fun String.asTFV() = TextFieldValue(this, TextRange(this.length))