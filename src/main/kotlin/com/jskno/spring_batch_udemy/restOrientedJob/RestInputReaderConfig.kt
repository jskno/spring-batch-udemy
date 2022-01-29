package com.jskno.spring_batch_udemy.restOrientedJob

import com.jskno.spring_batch_udemy.controller.StudentResponse
import org.springframework.batch.item.adapter.ItemReaderAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RestInputReaderConfig {

//    @Autowired
//    private lateinit var studentService: StudentService

    @Autowired
    private lateinit var studentService: LazyStudentService

    @Bean
    fun itemReaderAdapter(): ItemReaderAdapter<StudentResponse> {
        val itemReaderAdapter = ItemReaderAdapter<StudentResponse>()
        itemReaderAdapter.setTargetObject(studentService)
        itemReaderAdapter.setTargetMethod("getStudent")
        itemReaderAdapter.setArguments(arrayOf(34L, "Matheu"))
        return itemReaderAdapter
    }

}