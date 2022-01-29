package com.jskno.spring_batch_udemy.service

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.io.path.createTempDirectory

//@Service
class JobSchedulerService {

    @Autowired
    private lateinit var jobLauncher: JobLauncher

    @Autowired
    private lateinit var secondJob: Job

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    fun secondJobStarter() {
        val params = mapOf("currentTime" to JobParameter(System.currentTimeMillis()))
        val jobParameters = JobParameters(params)
        val jobExecution = jobLauncher.run(secondJob, jobParameters)
        println("Job Execution ID: ${jobExecution.id}")

    }
}