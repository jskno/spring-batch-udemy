package com.jskno.spring_batch_udemy.genericJobs

import com.jskno.spring_batch_udemy.SpringBatchUdemyApplication
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.PropertySource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@SpringBatchTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
//@ContextConfiguration(classes = [
//    InMemoryDatabaseConfig::class,
//    TaskletOrientedJob::class,
//    SpringBatchUdemyApplication::class])
@ContextConfiguration(locations = ["TaskletOrientedJob.kt"])
@EnableAutoConfiguration
//@PropertySource("goldcarjob-test.properties")
class TaskletOrientedJonIntegrationTest {

    @Autowired
    private lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Test
    fun testing() {

    }
}