package com.abhiram.databeatassessment.core.util

import android.util.Log

actual fun log(
    level: com.abhiram.databeatassessment.core.util.Log,
    tag: String,
    message: String
) {
    when (level) {
        is com.abhiram.databeatassessment.core.util.Log.Debug -> {
            Log.e(tag, message)
        }

        is com.abhiram.databeatassessment.core.util.Log.Error -> {
            Log.e(tag, message)
        }

        is com.abhiram.databeatassessment.core.util.Log.Info -> {
            Log.i(tag, message)
        }
    }
}