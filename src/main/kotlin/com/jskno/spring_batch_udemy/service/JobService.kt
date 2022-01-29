package com.jskno.spring_batch_udemy.service

import com.jskno.spring_batch_udemy.controller.JobParamsRequest
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.JobOperator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class JobService {

    @Autowired
    private lateinit var jobLauncher: JobLauncher

    @Autowired
    private lateinit var jobOperator: JobOperator

    @Autowired
    private lateinit var jobsLibrary: JobsLibrary

//    @Autowired
////    @Qualifier("firstJob")
//    private lateinit var firstJob: Job
//
//    @Autowired
////    @Qualifier("secondJob")
//    private lateinit var secondJob: Job

    @Async
    fun startJob(jobName: String) {
        val params = mapOf("currentTime" to JobParameter(System.currentTimeMillis()))
        val jobParameters = JobParameters(params)
//        if (jobName.equals("FirstJob")) {
//            jobLauncher.run(firstJob, jobParameters)
//        } else {
//            jobLauncher.run(secondJob, jobParameters)
//        }
        execute(jobName, jobParameters)
    }

    fun startJobWithParams(jobName: String, params: List<JobParamsRequest>) {
        val jobParams = mutableMapOf("currentTime" to JobParameter(System.currentTimeMillis()))
        params.forEach { jobParams[it.paramKey] = JobParameter(it.paramValue) }
        val jobParameters = JobParameters(jobParams)
        execute(jobName, jobParameters)
    }

    private fun execute(jobName: String, jobParameters: JobParameters) {
        val job = jobsLibrary.jobsMap[jobName]
        val jobExecution = jobLauncher.run(job!!, jobParameters)
        println("Job Execution ID: ${jobExecution.id}")
    }

    fun stopJob(executionId: Long) {
        jobOperator.stop(executionId)
    }
}