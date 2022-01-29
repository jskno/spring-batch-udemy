package com.jskno.spring_batch_udemy.csvOrientedJob

import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.item.file.FlatFileItemWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import java.util.*

@Configuration
class CsvOutputWriterConfig {

    @Bean
    @JobScope
    fun flatFileItemWriter(
        @Value("#{jobParameters[outputFile]}") fileSystemResource: FileSystemResource
    ): FlatFileItemWriter<CsvStudent> {
        val flatFileItemWriter = FlatFileItemWriter<CsvStudent>()
        flatFileItemWriter.setResource(fileSystemResource)
        flatFileItemWriter.setHeaderCallback { it.write("ID - FirstName - LastName - Email") }
        flatFileItemWriter.setLineAggregator { "${it.id} - ${it.firstName} - ${it.lastName} - ${it.email}" }
//        flatFileItemWriter.setLineAggregator(
//            object : DelimitedLineAggregator<CsvStudent>(){
//                override fun setDelimiter(delimiter: String) {
//                    super.setDelimiter(" | ")
//                }
//                override fun setFieldExtractor(fieldExtractor: FieldExtractor<CsvStudent>) {
//                    super.setFieldExtractor(fieldExtractor)
//                }
//            })
        flatFileItemWriter.setFooterCallback { it.write("Created @ ${Date()}") }
        return flatFileItemWriter
    }
}