package com.abhiram.databeatassessment

import com.abhiram.databeatassessment.feature_home.util.stringToLocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HelperFunctionsTest {
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