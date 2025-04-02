package com.abhiram.databeatassessment.core.util

class UnableToReachServerException(msg: String = "Unable to reach the server") : Exception(msg)

class AnErrorHasOccurredException(msg: String = "An error has occurred") : Exception(msg)

class NetworkException(val code: Int, msg: String) : Exception(msg)

expect fun isUnknownHostException(e: Throwable): Boolean

class UnknownPagingException(msg: String = "A paging error has occurred") : Exception(msg)