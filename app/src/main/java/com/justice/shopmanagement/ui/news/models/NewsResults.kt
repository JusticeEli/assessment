package com.justice.shopmanagement.ui.news.models

data class NewsResults(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)