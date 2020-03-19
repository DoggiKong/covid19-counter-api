package com.pandabyte.covid19counterapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Covid19CounterApiApplication

fun main(args: Array<String>) {
	runApplication<Covid19CounterApiApplication>(*args)
}
