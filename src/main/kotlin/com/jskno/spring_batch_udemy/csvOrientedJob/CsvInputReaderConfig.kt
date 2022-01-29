package com.jskno.spring_batch_udemy.csvOrientedJob

import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource

@Configuration
class CsvInputReaderConfig {

    @Bean
    @JobScope
    fun flatFileItemReader(
        @Value("#{jobParameters[inputFile]}") fileSystemResource: FileSystemResource
    ): FlatFileItemReader<CsvStudent> {

        val flatFileItemReader = FlatFileItemReader<CsvStudent>()

//        val url: URL = this.javaClass.getResource("/inputFiles/students.csv")
//        flatFileItemReader.setResource(
//            FileSystemResource(File(url.path)))

        flatFileItemReader.setResource(fileSystemResource)
        val lineTokenizer = DelimitedLineTokenizer()
        lineTokenizer.setNames("ID", "First Name", "Last Name", "Email")
        val beanWrapperFieldSetMapper = BeanWrapperFieldSetMapper<CsvStudent>()
        beanWrapperFieldSetMapper.setTargetType(CsvStudent::class.java)
        val defaultLineMapper = DefaultLineMapper<CsvStudent>()
        defaultLineMapper.setLineTokenizer(lineTokenizer)
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper)

        flatFileItemReader.setLineMapper(defaultLineMapper)
        flatFileItemReader.setLinesToSkip(1)
        return flatFileItemReader
    }

}