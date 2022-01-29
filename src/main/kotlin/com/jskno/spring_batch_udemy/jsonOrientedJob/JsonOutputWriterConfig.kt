package com.jskno.spring_batch_udemy.jsonOrientedJob

import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller
import org.springframework.batch.item.json.JsonFileItemWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Component
import java.lang.NullPointerException

@Configuration
class JsonOutputWriterConfig {

    @Bean(name = ["jsonOutputWriter"])
    @JobScope
    fun jsonOutputWriter(
        @Value("#{jobParameters[outputFile]}") fileSystemResource: FileSystemResource
    ): JsonFileItemWriter<JsonOutStudent> {
        val jsonOutputWriter = JsonFileItemWriter<JsonOutStudent>(
            fileSystemResource,
            JacksonJsonObjectMarshaller()
        )
        return jsonOutputWriter
    }

    @Bean(name = ["jsonOutputWriterWithError"])
    @JobScope
    fun jsonOutputWriterWithError(
        @Value("#{jobParameters[outputFile]}") fileSystemResource: FileSystemResource
    ): JsonFileItemWriter<JsonOutStudent> {
        return object : JsonFileItemWriter<JsonOutStudent>(
            fileSystemResource,
            JacksonJsonObjectMarshaller()
        ) {
            override fun doWrite(items: MutableList<out JsonOutStudent>): String {
                items.forEach {
                    if (it.id == 30L) {
                        println("Inside JsonItem WriterWithError")
                        throw NullPointerException()
                    }
                }
                return super.doWrite(items)
            }
        }
    }

}