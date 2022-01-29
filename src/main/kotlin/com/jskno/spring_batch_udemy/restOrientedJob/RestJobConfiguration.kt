package com.jskno.spring_batch_udemy.restOrientedJob

import com.jskno.spring_batch_udemy.controller.StudentResponse
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.adapter.ItemReaderAdapter
import org.springframework.batch.item.adapter.ItemWriterAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RestJobConfiguration {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    private lateinit var itemReaderAdapter: ItemReaderAdapter<StudentResponse>

    @Autowired
    private lateinit var restStudentProcessor: RestStudentProcessor

    @Autowired
    private lateinit var restOutputWriter: ItemWriterAdapter<StudentRequest>

    @Bean
    fun restInputJob(): Job {
        return jobBuilderFactory.get("RestInputJob")
            .incrementer(RunIdIncrementer())
            .start(restInputStep())
            .build()
    }

    @Bean
    fun restInputStep(): Step {
        return stepBuilderFactory.get("RestInputStep")
            .chunk<StudentResponse, StudentRequest>(3)
            .reader(itemReaderAdapter)
            .processor(restStudentProcessor)
            .writer(restOutputWriter)
            .build()
    }
}