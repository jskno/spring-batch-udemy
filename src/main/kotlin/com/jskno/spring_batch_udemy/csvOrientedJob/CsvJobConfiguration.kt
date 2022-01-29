package com.jskno.spring_batch_udemy.csvOrientedJob

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.FlatFileItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CsvJobConfiguration {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    private lateinit var flatFileItemReader: FlatFileItemReader<CsvStudent>

    @Autowired
    private lateinit var flatFileItemWriter: FlatFileItemWriter<CsvStudent>

    @Bean
    fun csvInputJob(): Job {
        return jobBuilderFactory.get("CsvInputJob")
            .incrementer(RunIdIncrementer())
            .start(csvInputStep())
            .build()
    }

    @Bean
    fun csvInputStep(): Step {
        return stepBuilderFactory.get("CsvInputStep")
            .chunk<CsvStudent, CsvStudent>(3)
            .reader(flatFileItemReader)
            .writer(flatFileItemWriter)
            .build()
    }
}