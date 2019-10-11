package pl.kreft.csv_converter.control

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.util.Date

@Service
class ConverterService @Autowired internal constructor(
        @Value("\${self.csv.file-path}") val csvFilePath: String
) {
    private val log = KotlinLogging.logger {}
    private val CSV_EXTENSION = ".csv"
    private val DATE_PATTERN = "dd_MM_yyyy"

    fun convertToCSV(name: String, listOfdata: JsonNode): String {
        log.info("Preparing csv file")
        log.debug("Preparing csv file with the followind data, name: $name and data: $listOfdata")
        val dataPath = prepareFilePath(name)
        val csvSchemaBuilder = CsvSchema.builder()
        val firstObject = listOfdata.elements().next()
        firstObject.fieldNames().forEachRemaining { fieldName -> csvSchemaBuilder.addColumn(fieldName) }
        val csvSchema = csvSchemaBuilder.build().withHeader()
        val csvMapper = CsvMapper()
        csvMapper.writerFor(JsonNode::class.java)
                .with(csvSchema)
                .writeValue(File(dataPath), listOfdata)

        log.info("CSV file prepared")
        return dataPath
    }

    private fun prepareFilePath(name: String): String {
        log.info("Preparing CSV file path with input data: $name")
        val dateFromInstant = Date.from(Instant.now().minus(Duration.ofDays(1)))
        val formatter = SimpleDateFormat(DATE_PATTERN)
        val formattedDate = formatter.format(dateFromInstant)
        val filePath = "$csvFilePath/$name$formattedDate$CSV_EXTENSION"
        log.info("CSV file path prepared: $filePath")
        return filePath
    }
}