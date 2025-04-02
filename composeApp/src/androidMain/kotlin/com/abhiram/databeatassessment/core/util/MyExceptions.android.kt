package com.abhiram.databeatassessment.core.util

import java.net.UnknownHostException

actual fun isUnknownHostException(e: Throwable): Boolean {
    return e is UnknownHostException || e.cause is UnknownHostException
}