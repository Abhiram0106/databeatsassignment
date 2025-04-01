package com.abhiram.databeatassessment.feature_home.util

import android.util.Log

actual fun log(
    level: com.abhiram.databeatassessment.feature_home.util.Log,
    tag: String,
    message: String
) {
    when (level) {
        is com.abhiram.databeatassessment.feature_home.util.Log.Debug -> {
            Log.e(tag, message)
        }

        is com.abhiram.databeatassessment.feature_home.util.Log.Error -> {
            Log.e(tag, message)
        }

        is com.abhiram.databeatassessment.feature_home.util.Log.Info -> {
            Log.i(tag, message)
        }
    }
}