package com.abhiram.databeatassessment

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform