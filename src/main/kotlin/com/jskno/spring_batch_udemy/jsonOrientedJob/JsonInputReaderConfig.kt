package com.jskno.spring_batch_udemy.jsonOrientedJob

import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.item.json.JacksonJsonObjectReader
import org.springframework.batch.item.json.JsonItemReader
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource

@Configuration
class JsonInputReaderConfig {

    @Bean
    @JobScope
    fun jsonItemReader(@Value("#{jobParameters[inputFile]}") fileSystemResource: FileSystemResource):
            JsonItemReader<JsonStudent> {

        val jsonItemReader = JsonItemReader<JsonStudent>()
        jsonItemReader.setResource(fileSystemResource)
        jsonItemReader.setJsonObjectReader(JacksonJsonObjectReader(JsonStudent::class.java))
        jsonItemReader.setCurrentItemCount(2)
        jsonItemReader.setMaxItemCount(8)
        return jsonItemReader
    }

}