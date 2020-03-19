package com.pandabyte.covid19counterapi.v1.controllers

import com.pandabyte.covid19counterapi.CovidStatisticService
import com.pandabyte.covid19counterapi.v1.models.CovidStatisticCountries
import com.pandabyte.covid19counterapi.v1.models.CovidStatisticSummary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CovidStatisticController(
        @Autowired
        val covidStatisticService: CovidStatisticService
) {
    @GetMapping("/v1/statistics/summary")
    fun getCovidStatistics(): CovidStatisticSummary {
        return covidStatisticService.getCovidStatisticSummary()
    }

    @GetMapping("/v1/statistics/countries")
    fun getCovidStatisticsCountries(): CovidStatisticCountries {
        return covidStatisticService.getCovidCountriesList()
    }
}