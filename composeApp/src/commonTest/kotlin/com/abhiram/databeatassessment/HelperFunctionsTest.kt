package com.abhiram.databeatassessment

import androidx.compose.ui.test.ExperimentalTestApi
import com.abhiram.databeatassessment.feature_home.util.stringToLocalDate
import com.abhiram.databeatassessment.feature_home.util.toFormattedString
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ExperimentalTestApi::class)
class HelperFunctionsTest {

    @Test
    fun `toFormattedString should format morning time correctly`() {
        val dateTime = LocalDateTime(2025, 4, 25, 9, 30)
        val expected = "25 April 2025, 09:30 AM"
        val actual = dateTime.toFormattedString("AM", "PM")
        assertEquals(expected, actual)
    }

    @Test
    fun `toFormattedString should format afternoon time correctly`() {
        val dateTime = LocalDateTime(2025, 6, 27, 14, 45, 0)
        val expected = "27 June 2025, 02:45 PM"
        val actual = dateTime.toFormattedString("AM", "PM")
        assertEquals(expected, actual)
    }

    @Test
    fun `toFormattedString should format midnight correctly`() {
        val dateTime = LocalDateTime(2025, 10, 27, 0, 0, 0)
        val expected = "27 October 2025, 12:00 AM"
        val actual = dateTime.toFormattedString("AM", "PM")
        assertEquals(expected, actual)
    }

    @Test
    fun `toFormattedString should format noon correctly`() {
        val dateTime = LocalDateTime(2025, 10, 27, 12, 0, 0)
        val expected = "27 October 2025, 12:00 PM"
        val actual = dateTime.toFormattedString("AM", "PM")
        assertEquals(expected, actual)
    }

    @Test
    fun `toFormattedString should use custom AM and PM`() {
        val dateTime = LocalDateTime(2025, 12, 1, 10, 0, 0)
        val customAm = "Morning"
        val customPm = "Evening"
        val expected = "01 December 2025, 10:00 Morning"
        val actual = dateTime.toFormattedString(customAm, customPm)
        assertEquals(expected, actual)

        val dateTime2 = LocalDateTime(2026, 1, 1, 22, 0, 0)
        val expected2 = "01 January 2026, 10:00 Evening"
        val actual2 = dateTime2.toFormattedString(customAm, customPm)
        assertEquals(expected2, actual2)
    }

    @Test
    fun `stringToLocalDate should parse valid date and time`() {
        val dateTimeString = "2025-04-02T10:30:00"
        val expected = LocalDateTime(2025, 4, 2, 10, 30)
        assertEquals(expected, stringToLocalDate(dateTimeString))
    }

    @Test
    fun `stringToLocalDate should parse valid date and time at new year`() {
        val dateTimeString = "2025-01-01T00:00:00"
        val expected = LocalDateTime(2025, 1, 1, 0, 0, 0)
        assertEquals(expected, stringToLocalDate(dateTimeString))
    }

    @Test
    fun `stringToLocalDate should return null for invalid date and time`() {
        val dateTimeString = "invalid-date-time"
        assertNull(stringToLocalDate(dateTimeString))
    }

    @Test
    fun `stringToLocalDate should return null for date only`() {
        val dateTimeString = "2026-10-26"
        assertNull(stringToLocalDate(dateTimeString))
    }

    @Test
    fun `stringToLocalDate should parse at first year`() {
        val dateTimeString = "0001-01-01T00:00:00"
        val expected = LocalDateTime(1, 1, 1, 0, 0, 0)
        assertEquals(expected, stringToLocalDate(dateTimeString))
    }

    @Test
    fun `stringToLocalDate should parse at 9999th year`() {
        val dateTimeString = "9999-12-31T23:59:59"
        val expected = LocalDateTime(9999, 12, 31, 23, 59, 59)
        assertEquals(expected, stringToLocalDate(dateTimeString))
    }
}