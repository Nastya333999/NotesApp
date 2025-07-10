package com.notescollection.app.notes.presentation.noteList.models

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.notescollection.app.R
import com.notescollection.app.notes.domain.models.NoteModel
import java.time.Instant
import java.time.Year
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlinx.serialization.Serializable
import java.time.Duration
import java.time.OffsetDateTime

@Serializable
data class NoteUiModel(
    val id: String,
    val date: String,
    val title: String,
    val description: String,
    val createdAt: String,
    val lastEditedAt: String,
    val isDeleted: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
fun NoteModel.toUiModel(context: Context) = NoteUiModel(
    id = id,
    date = createdAt.toPrettyDate(),
    title = title,
    description = content,
    createdAt = createdAt.toPrettyTime(context),
    lastEditedAt = lastEditedAt.toPrettyTime(context)
)

@RequiresApi(Build.VERSION_CODES.O)
fun String.toPrettyDate(): String = runCatching {
    val zoned = Instant.parse(this).atZone(ZoneId.systemDefault())
    val pattern = if (zoned.year == Year.now().value) "d MMM"
    else "d MMM yyyy"

    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)
    zoned.format(formatter)
}.getOrElse { this }

@RequiresApi(Build.VERSION_CODES.O)
fun String.toPrettyTime(context: Context): String = try {
    val parsed = OffsetDateTime.parse(this)
    val diff = Duration.between(parsed, OffsetDateTime.now())

    if (diff.toMinutes() < 5) {
        context.getString(R.string.just_now)
    } else {
        parsed.format(
            DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.ENGLISH)
        )
    }
} catch (e: Exception) {
    this
}
