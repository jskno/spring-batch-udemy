package com.jskno.spring_batch_udemy.restOrientedJob

import org.springframework.batch.item.adapter.ItemWriterAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class RestOutputWriterConfig {

    @Autowired
    private lateinit var studentService: LazyStudentService
    @Bean
    fun restOutputWriter(): ItemWriterAdapter<StudentRequest> {
        val restOutputWriter = ItemWriterAdapter<StudentRequest>()
        restOutputWriter.setTargetObject(studentService)
        restOutputWriter.setTargetMethod("restCallToCreateStudent")
        return restOutputWriter
    }
}