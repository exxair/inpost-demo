package com.example.demo.controller

import com.example.demo.model.DataRecord
import com.example.demo.model.Income
import com.example.demo.service.DemoService
import spock.lang.Specification

import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class DemoControllerTest extends Specification {

    private final DemoService demoService = Mock()

    private final DemoController demoController = new DemoController(demoService)

    def "should return correct average value"() {
        given:
        def income1 = new Income("PLN", 5)
        def income2 = new Income("PLN", 10)
        def record1 = new DataRecord(UUID.fromString("2a150c78-1aac-4478-8710-dd76088597ae"), "name", "MERCHANT", 1, "desc", OffsetDateTime.parse("2023-06-06T09:28:21Z"), [income1])
        def record2 = new DataRecord(UUID.fromString("2a150c78-1aac-4478-8710-dd76088597ae"), "name", "MERCHANT", 1, "desc", OffsetDateTime.parse("2023-06-06T09:28:21Z"), [income2])
        def records = [
                record1,
                record2
        ]
        demoService.getRecordsForRegion(_) >> records
        def dateSince = LocalDate.parse("20230605", DateTimeFormatter.ofPattern("yyyyMMdd"))
        def dateUntil = LocalDate.parse("20230607", DateTimeFormatter.ofPattern("yyyyMMdd"))

        when:
        def result = demoController.getAverage("PL", "PLN", ["MERCHANT"], dateSince, dateUntil)

        then:
        result == 7.5
    }

}
