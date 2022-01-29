package com.jskno.spring_batch_udemy.jsonOrientedJob

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.json.JsonFileItemWriter
import org.springframework.batch.item.json.JsonItemReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonJobConfiguration {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    private lateinit var jsonItemReader: JsonItemReader<JsonStudent>

    @Autowired
    private lateinit var jsonItemProcessor: JsonStudentProcessor

    @Autowired
    private lateinit var jsonOutputWriter: JsonFileItemWriter<JsonOutStudent>

    @Bean
    fun jsonInputJob(): Job {
        return jobBuilderFactory.get("JsonInputJob")
            .incrementer(RunIdIncrementer())
            .start(jsonInputStep())
            .build()
    }

    @Bean
    fun jsonInputStep(): Step {
        return stepBuilderFactory.get("JsonInputStep")
            .chunk<JsonStudent, JsonOutStudent>(3)
            .reader(jsonItemReader)
            .processor(jsonItemProcessor)
            .writer(jsonOutputWriter)
            .build()
    }
}