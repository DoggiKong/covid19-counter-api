package com.pandabyte.covid19counterapi.v1.models

data class CovidStatisticCountry(
        val country: String,
        val cases: Long,
        val todayCases: Long,
        val deaths: Long,
        val todayDeaths: Long,
        val recovered: Long,
        val activeCases: Long,
        val critical: Long
)