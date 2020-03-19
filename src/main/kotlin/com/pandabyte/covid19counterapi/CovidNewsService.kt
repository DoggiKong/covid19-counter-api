package com.pandabyte.covid19counterapi

import com.pandabyte.covid19counterapi.v1.models.CovidApNews
import com.pandabyte.covid19counterapi.v1.models.CovidApNewsList
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CovidNewsService(
        @Value("\${source.apNews}")
        val apNewsUrl: String
) {
    fun getNews(): CovidApNewsList {
        return scrapeAPNews()
    }

    fun scrapeAPNews(): CovidApNewsList {
        val document = Jsoup.connect("$apNewsUrl/VirusOutbreak").get()
        val newsElements = document.select("article.feed > div.FeedCard[data-key$=feed-card-wire-story-with-image]")
        val newsList = newsElements.map {
            CovidApNews(
                    it.select(".CardHeadline").select("h1").text(),
                    it.select("div.content").text(),
                    "${apNewsUrl}${it.select(".CardHeadline > a[href]").attr("href")}"
            )
        }
        return CovidApNewsList(newsList)
    }
}