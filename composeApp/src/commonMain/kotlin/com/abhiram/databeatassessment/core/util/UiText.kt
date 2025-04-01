package com.abhiram.databeatassessment.core.util

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {
    data class DynamicString(val value: String) : UiText
    class StringResourceId(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResourceId -> stringResource(resource = id, formatArgs = args)
        }
    }

    suspend fun asStringNonComposable(): String {
        return when(this) {
            is DynamicString -> value
            is StringResourceId -> getString(id, args)
        }
    }
}