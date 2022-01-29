package com.jskno.spring_batch_udemy.faultToleranceJob

import com.jskno.spring_batch_udemy.csvOrientedJob.CsvStudent
import com.jskno.spring_batch_udemy.jsonOrientedJob.JsonOutStudent
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import java.lang.NullPointerException

@Component
class CsvToJsonStudentProcessor : ItemProcessor<CsvStudent, JsonOutStudent> {

    override fun process(item: CsvStudent): JsonOutStudent? {
        if (item.id == 6L) {
            println("Inside JsonItem Processor")
            throw NullPointerException()
        }
        return JsonOutStudent(
            item.id * 10,
            item.firstName,
            item.lastName,
            item.email
        )
    }
}