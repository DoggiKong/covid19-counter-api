package com.pandabyte.covid19counterapi.v1.controllers

import com.pandabyte.covid19counterapi.CovidNewsService
import com.pandabyte.covid19counterapi.v1.models.CovidApNewsList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class CovidNewsController(
        @Autowired
        val covidNewsService: CovidNewsService
) {
    @GetMapping("/v1/news")
    fun getNews(): CovidApNewsList {
        return covidNewsService.getNews()
    }
}