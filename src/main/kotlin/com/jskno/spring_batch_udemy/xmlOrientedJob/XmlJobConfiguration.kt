package com.jskno.spring_batch_udemy.xmlOrientedJob

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.xml.StaxEventItemReader
import org.springframework.batch.item.xml.StaxEventItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class XmlJobConfiguration {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    private lateinit var staxEventItemReader: StaxEventItemReader<XmlStudent>

    @Autowired
    private lateinit var xmlStudentWriter: StaxEventItemWriter<XmlStudent>

    @Bean
    fun xmlInputJob(): Job {
        return jobBuilderFactory.get("XmlInputJob")
            .incrementer(RunIdIncrementer())
            .start(xmlInputStep())
            .build()
    }

    @Bean
    fun xmlInputStep(): Step {
        return stepBuilderFactory.get("XmlInputStep")
            .chunk<XmlStudent, XmlStudent>(4)
            .reader(staxEventItemReader)
            .writer(xmlStudentWriter)
            .build()
    }
}