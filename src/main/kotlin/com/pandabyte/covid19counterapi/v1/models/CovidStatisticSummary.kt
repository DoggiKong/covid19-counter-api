package com.pandabyte.covid19counterapi.v1.models

data class CovidStatisticSummary(
        val cases: Long,
        val deaths: Long,
        val recovered: Long,
        val updated: Long
)