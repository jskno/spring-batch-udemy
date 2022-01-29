package com.jskno.spring_batch_udemy.genericJobs.listener

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class FistJobListener: JobExecutionListener {

    override fun beforeJob(jobExecution: JobExecution) {
        println("Before Job ${jobExecution.jobInstance.jobName}")
        println("Job Params ${jobExecution.jobParameters}")
        println("Job Execution Context ${jobExecution.executionContext}")

        jobExecution.executionContext.put("jec", "NewVariable")
    }

    override fun afterJob(jobExecution: JobExecution) {
        println("After Job ${jobExecution.jobInstance.jobName}")
        println("Job Params ${jobExecution.jobParameters}")
        println("Job Execution Context ${jobExecution.executionContext}")
    }
}