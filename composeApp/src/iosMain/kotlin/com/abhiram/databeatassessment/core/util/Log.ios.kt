package com.abhiram.databeatassessment.core.util

import com.abhiram.databeatassessment.core.util.Log
import platform.Foundation.NSLog

actual fun log(
    level: Log,
    tag: String,
    message: String
) {
    val logMessage = "[$tag] $message"
    when (level) {
        is Log.Debug -> NSLog("DEBUG: $logMessage")
        is Log.Error -> NSLog("ERROR: $logMessage")
        is Log.Info -> NSLog("INFO: $logMessage")
    }
}