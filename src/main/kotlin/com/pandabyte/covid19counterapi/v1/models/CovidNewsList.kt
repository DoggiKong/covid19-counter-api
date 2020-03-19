package com.pandabyte.covid19counterapi.v1.models

data class CovidApNewsList(
        val news: List<CovidApNews>
)

data class CovidApNews(
        val title: String,
        val description: String,
        val link: String
)