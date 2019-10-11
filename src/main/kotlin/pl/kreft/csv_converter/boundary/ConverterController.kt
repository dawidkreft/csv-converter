package pl.kreft.csv_converter.boundary

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.kreft.csv_converter.control.ConverterService

@RestController
@RequestMapping("/convert")
class ConverterController @Autowired internal constructor(
        private val converterService: ConverterService
) {
    @PutMapping
    fun convertWeightMeasurements(@RequestParam name: String, @RequestBody measurements: JsonNode): String {
        return converterService.convertToCSV(name, measurements)
    }
}