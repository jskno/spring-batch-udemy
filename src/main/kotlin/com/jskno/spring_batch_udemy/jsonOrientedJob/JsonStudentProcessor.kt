package com.jskno.spring_batch_udemy.jsonOrientedJob

import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class JsonStudentProcessor: ItemProcessor<JsonStudent, JsonOutStudent> {

    override fun process(item: JsonStudent): JsonOutStudent? {
        println("Inside JsonItem Processor")
        return JsonOutStudent(
            item.id,
            item.firstName,
            item.lastName,
            item.email
        )
    }
}