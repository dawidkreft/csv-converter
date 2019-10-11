package pl.kreft.csv_converter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CsvConverterApplication

fun main(args: Array<String>) {
    runApplication<CsvConverterApplication>(*args)
}
