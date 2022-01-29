package com.jskno.spring_batch_udemy.xmlOrientedJob

import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.item.xml.StaxEventItemReader
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import org.springframework.oxm.jaxb.Jaxb2Marshaller

@Configuration
class XmlInputReaderConfig {

    @Bean
    @JobScope
    fun staxEventItemReader(@Value("#{jobParameters[inputFile]}") fileSystemResource: FileSystemResource):
            StaxEventItemReader<XmlStudent> {
        val staxEventItemReader = StaxEventItemReader<XmlStudent>()
        staxEventItemReader.setResource(fileSystemResource)
        staxEventItemReader.setFragmentRootElementName("student")
        val marshaller = Jaxb2Marshaller()
        marshaller.setClassesToBeBound(XmlStudent::class.java)
        staxEventItemReader.setUnmarshaller(marshaller)
        return staxEventItemReader
    }

}