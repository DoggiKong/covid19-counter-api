package com.pandabyte.covid19counterapi.v1.controllers

import com.pandabyte.covid19counterapi.CovidStatisticService
import com.pandabyte.covid19counterapi.v1.models.CovidStatisticCountries
import com.pandabyte.covid19counterapi.v1.models.CovidStatisticSummary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["https://covid19-counter.herokuapp.com"], maxAge = 3600)
@RestController
@RequestMapping("/v1/statistics")
class CovidStatisticController(
        @Autowired
        val covidStatisticService: CovidStatisticService
) {
    @GetMapping("/summary")
    fun getCovidStatistics(): CovidStatisticSummary {
        return covidStatisticService.getCovidStatisticSummary()
    }

    @GetMapping("/countries")
    fun getCovidStatisticsCountries(): CovidStatisticCountries {
        return covidStatisticService.getCovidCountriesList()
    }
}