package com.abhiram.databeatassessment.feature_home.domain

enum class NewsCategories(val displayText: String, val query: String?) {
    NONE("none", null),
    BUSINESS("Business", "business"),
    ENTERTAINMENT("Entertainment", "entertainment"),
    GENERAL("General", "general"),
    HEALTH("Health", "health"),
    SCIENCE("Science", "science"),
    SPORTS("Sports", "sports"),
    TECHNOLOGY("Technology", "technology")
}