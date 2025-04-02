package com.abhiram.databeatassessment.feature_home.util

import androidx.compose.runtime.Composable
import databeatassessment.composeapp.generated.resources.Res
import databeatassessment.composeapp.generated.resources.am
import databeatassessment.composeapp.generated.resources.pm
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.stringResource

/**
 * Formats a [LocalDateTime] object into a human-readable string.
 *
 * The output string will follow the format: "DayOfMonth MonthName Year, Hour:Minute AM/PM".
 * For example: "15 November 2023, 03:30 PM".
 *
 * @param am The string to use for the AM marker. Defaults to the localized "am" string resource.
 * @param pm The string to use for the PM marker. Defaults to the localized "pm" string resource.
 * @return A formatted string representation of the [LocalDateTime].
 *
 * Example Usage:
 * ```kotlin
 * val dateTime = LocalDateTime.of(2023, 11, 15, 15, 30)
 * val formattedString = dateTime.toFormattedString()
 * // formattedString will be "15 November 2023, 03:30 PM" (assuming default "am" and "pm" resources)
 *
 * val formattedStringCustom = dateTime.toFormattedString(am = "Morning", pm = "Evening")
 * // formattedStringCustom will be "15 November 2023, 03:30 Evening"
 * ```
 */
fun LocalDateTime.toFormattedString(am: String, pm: String): String {
    val formatter = LocalDateTime.Format {
        dayOfMonth()
        char(' ')
        monthName(MonthNames.ENGLISH_FULL)
        char(' ')
        year()
        chars(", ")
        amPmHour()
        char(':')
        minute()
        char(' ')
        amPmMarker(am = am, pm = pm)
    }

    return this.format(formatter)
}

fun stringToLocalDate(dateTimeString: String): LocalDateTime? {
    return try {
        LocalDateTime.parse(dateTimeString.dropLastWhile { it == 'Z' }) // drop the Z in "2025-03-07T17:31:00Z"
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }
}