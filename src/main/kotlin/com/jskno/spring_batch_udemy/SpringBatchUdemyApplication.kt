package com.jskno.spring_batch_udemy

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import java.time.LocalDateTime
import java.util.*

@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
@EnableScheduling
class SpringBatchUdemyApplication {

//    @Bean
//    fun init(context: AnnotationConfigApplicationContext, args: ApplicationArguments) = CommandLineRunner {
//        val jobLauncher = context.getBean(JobLauncher::class.java)
//        val argsJobName = args.nonOptionArgs
//        if (argsJobName?.size == 1 && argsJobName.contains("GOLDCAR")) {
//            println("Name ${args.getOptionValues("jobName")}")
//            val goldcarJob = context.getBean("secondJob", Job::class.java)
//            val jobExecution = jobLauncher.run(
//                goldcarJob,
//                JobParametersBuilder()
//                    .addDate("date", Date()).toJobParameters()
//            )
//        } else {
//            val europcarJob = context.getBean("firstJob", Job::class.java)
//            val jobExecution = jobLauncher.run(
//                europcarJob,
//                JobParametersBuilder()
//                    .addDate("date", Date()).toJobParameters())
//        }
//    }

}

fun main(args: Array<String>) {
    runApplication<SpringBatchUdemyApplication>(*args)

//    AnnotationConfigApplicationContext(SpringBatchUdemyApplication::class.java).use { context ->
//        val jobLauncher = context.getBean(JobLauncher::class.java)
//        if (args[0] == "GOLDCAR") {
//            val goldcarJob = context.getBean("secondJob", Job::class.java)
//            val jobExecution = jobLauncher.run(goldcarJob, JobParametersBuilder().toJobParameters())
//        } else {
//            val europcarJob = context.getBean("firstJob", Job::class.java)
//            val jobExecution = jobLauncher.run(europcarJob, JobParametersBuilder().toJobParameters())
//        }
//
//        System.exit(0)
//    }
}
