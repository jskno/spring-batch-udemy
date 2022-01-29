package com.jskno.spring_batch_udemy.jdbcOrientedJob

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.JdbcCursorItemReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JdbcJobConfiguration {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    private lateinit var jdbcCursorItemReader: JdbcCursorItemReader<JdbcStudent>

    @Autowired
    @Qualifier("jdbcOutputWriter")
    private lateinit var jdbcOutputWriter: JdbcBatchItemWriter<JdbcStudent>

    @Autowired
    @Qualifier("jdbcOutputWriterPrepareStatement")
    private lateinit var jdbcOutputWriterPrepareStatement: JdbcBatchItemWriter<JdbcStudent>

    @Bean
    fun jdbcInputJob(): Job {
        return jobBuilderFactory.get("JdbcInputJob")
            .incrementer(RunIdIncrementer())
            .start(jdbcInputStep())
            .build()
    }

    @Bean
    fun jdbcInputStep(): Step {
        return stepBuilderFactory.get("JdbcInputStep")
            .chunk<JdbcStudent, JdbcStudent>(2)
            .reader(jdbcCursorItemReader)
//            .writer(jdbcOutputWriter)
            .writer(jdbcOutputWriterPrepareStatement)
            .build()
    }
}