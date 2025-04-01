package com.abhiram.databeatassessment.feature_home.util

actual fun isUnknownHostException(e: Throwable): Boolean {
    return e is java.net.UnknownHostException || e.cause is java.net.UnknownHostException
}