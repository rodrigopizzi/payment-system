package dev.h2r.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication(scanBasePackages = ["dev.h2r"])
@PropertySource("classpath:application.properties")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}


