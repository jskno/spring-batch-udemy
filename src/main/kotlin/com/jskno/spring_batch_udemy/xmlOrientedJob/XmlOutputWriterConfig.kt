package com.jskno.spring_batch_udemy.xmlOrientedJob

import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.item.xml.StaxEventItemWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import org.springframework.oxm.jaxb.Jaxb2Marshaller

@Configuration
class XmlOutputWriterConfig {

    @Bean
    @JobScope
    fun xmlOutputWriter(
        @Value("#{jobParameters[outputFile]}") fileSystemResource: FileSystemResource
    ): StaxEventItemWriter<XmlStudent> {
        val xmlOutputWriter = StaxEventItemWriter<XmlStudent>()
        xmlOutputWriter.setResource(fileSystemResource)
        xmlOutputWriter.rootTagName = "stds"
        val marshaller = Jaxb2Marshaller()
        marshaller.setClassesToBeBound(XmlStudent::class.java)
        xmlOutputWriter.setMarshaller(marshaller)
        return xmlOutputWriter
    }
}