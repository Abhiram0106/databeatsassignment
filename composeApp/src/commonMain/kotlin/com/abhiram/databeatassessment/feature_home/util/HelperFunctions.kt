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

@Composable
fun LocalDateTime.toFormattedString(
    am: String = stringResource(Res.string.am),
    pm: String = stringResource(Res.string.pm)
): String {
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