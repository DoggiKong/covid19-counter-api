package com.pandabyte.covid19counterapi

import com.pandabyte.covid19counterapi.v1.models.CovidStatisticCountries
import com.pandabyte.covid19counterapi.v1.models.CovidStatisticCountry
import com.pandabyte.covid19counterapi.v1.models.CovidStatisticSummary
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CovidStatisticService(
        @Value("\${source.covidStatistics}")
        private val covidStatisticsUrl: String
) {
    fun getCovidStatisticSummary(): CovidStatisticSummary {
        return scrapeSummaryStat()
    }

    fun getCovidCountriesList(): CovidStatisticCountries {
        return scrapeCountriesList()
    }

    private fun scrapeSummaryStat(): CovidStatisticSummary {
        val document = Jsoup.connect(covidStatisticsUrl).get()
        val totalStats = document.select(".maincounter-number > span")
        return CovidStatisticSummary(totalStats[0].text().replace(",", "").toLong(),
                totalStats[1].text().replace(",", "").toLong(),
                totalStats[2].text().replace(",", "").toLong(),
                Instant.now().toEpochMilli())
    }

    private fun scrapeCountriesList(): CovidStatisticCountries {
        val document = Jsoup.connect(covidStatisticsUrl).get()
        val countryRows = document.select("#main_table_countries_today > tbody")[0].select("tr")

        val countriesList: List<CovidStatisticCountry> = countryRows.map { it ->
            val elements: ArrayList<String> = ArrayList()
            it.select("td").map {
                val text = when {
                    it.text().isNotEmpty() -> it.text()
                    else -> "0"
                }
                elements.add(text)
            }
            CovidStatisticCountry(
                    elements[0],
                    elements[1].replace(",", "").toLong(),
                    elements[2].replace(Regex("[+,]+"), "").toLong(),
                    elements[3].replace(",", "").toLong(),
                    elements[4].replace(Regex("[+,]+"), "").toLong(),
                    elements[5].replace(",", "").toLong(),
                    elements[6].replace(",", "").toLong(),
                    elements[7].replace(",", "").toLong()
            )
        }
        return CovidStatisticCountries(countriesList)
    }
}