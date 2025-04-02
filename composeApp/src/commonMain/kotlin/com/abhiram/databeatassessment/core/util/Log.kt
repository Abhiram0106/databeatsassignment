package com.abhiram.databeatassessment.core.util

sealed class Log(loc: String, msg: String) {

    data class Error(val location: String, val message: String) : Log(location, message)
    data class Info(val location: String, val message: String) : Log(location, message)
    data class Debug(val location: String, val message: String) : Log(location, message)

    init {
        log(level = this, tag = loc, message = msg)
    }
}

expect fun log(level: Log, tag: String, message: String)