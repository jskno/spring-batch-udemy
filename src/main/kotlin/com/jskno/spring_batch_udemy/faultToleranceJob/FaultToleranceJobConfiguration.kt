package com.jskno.spring_batch_udemy.faultToleranceJob

import com.jskno.spring_batch_udemy.csvOrientedJob.CsvStudent
import com.jskno.spring_batch_udemy.jsonOrientedJob.JsonOutStudent
import org.springframework.batch.core.Job
import org.springframework.batch.core.SkipListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.json.JsonFileItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FaultToleranceJobConfiguration {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    private lateinit var csvInputReader: FlatFileItemReader<CsvStudent>

    @Autowired
    private lateinit var csvToJsonStudentProcessor: CsvToJsonStudentProcessor

    @Autowired
    @Qualifier("jsonOutputWriter")
    private lateinit var jsonOutputWriter: JsonFileItemWriter<JsonOutStudent>

    @Autowired
    @Qualifier("jsonOutputWriterWithError")
    private lateinit var jsonOutputWriterWithError: JsonFileItemWriter<JsonOutStudent>

    @Autowired
    private lateinit var skipListener: SkipListenerByAnnotatedMethods

    @Autowired
    private lateinit var skipListenerByImpl: SkipListener<CsvStudent, JsonOutStudent>

    @Bean
    fun faultToleranceJob(): Job {
        return jobBuilderFactory.get("FaultToleranceJob")
            .incrementer(RunIdIncrementer())
            .start(chunkStep())
            .build()
    }

    private fun chunkStep(): Step {
        return stepBuilderFactory.get("FaultToleranceStep")
            .chunk<CsvStudent, JsonOutStudent>(3)
            .reader(csvInputReader)
            .processor(csvToJsonStudentProcessor)
//            .writer(jsonOutputWriter)
            .writer(jsonOutputWriterWithError)
            .faultTolerant()
//            .skip(FlatFileParseException::class.java)
//            .skip(NullPointerException::class.java)
            .skip(Throwable::class.java)
//            .skipLimit(1)
//            .skipLimit(Int.MAX_VALUE)
//            .skipPolicy(AlwaysSkipItemSkipPolicy())
            .retryLimit(3)
            .retry(Throwable::class.java)
            .skipLimit(10)
//            .listener(skipListener)
            .listener(skipListenerByImpl)
            .build()
    }




}