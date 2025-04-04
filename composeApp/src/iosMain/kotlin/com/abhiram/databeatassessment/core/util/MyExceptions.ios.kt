package com.abhiram.databeatassessment.core.util

import platform.Foundation.NSError
import platform.Foundation.NSURLErrorCannotFindHost
import platform.Foundation.NSURLErrorNotConnectedToInternet

actual fun isUnknownHostException(e: Throwable): Boolean {
    val nsError = e as? NSError
    return nsError?.code == NSURLErrorNotConnectedToInternet || nsError?.code == NSURLErrorCannotFindHost
}