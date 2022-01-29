package com.jskno.spring_batch_udemy.restOrientedJob

import com.jskno.spring_batch_udemy.controller.StudentResponse
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class RestStudentProcessor: ItemProcessor<StudentResponse, StudentRequest> {

    override fun process(item: StudentResponse): StudentRequest? {
        return StudentRequest(
            item.id,
            item.firstName,
            item.lastName,
            item.email
        )
    }
}