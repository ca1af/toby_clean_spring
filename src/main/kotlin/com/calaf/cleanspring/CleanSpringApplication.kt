package com.calaf.cleanspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CleanSpringApplication

fun main(args: Array<String>) {
    runApplication<CleanSpringApplication>(*args)
}
