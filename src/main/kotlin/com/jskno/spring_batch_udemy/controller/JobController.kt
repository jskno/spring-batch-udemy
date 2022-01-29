package com.jskno.spring_batch_udemy.controller

import com.jskno.spring_batch_udemy.service.JobService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/job")
class JobController {

    @Autowired
    private lateinit var jobService: JobService

    @GetMapping("/start/{jobName}")
    fun startJob(@PathVariable jobName: String): String {
        jobService.startJob(jobName)
        return "Job Started..."
    }

    @GetMapping("/params/{jobName}")
    fun startJobWithParams(@PathVariable jobName: String, @RequestBody params: List<JobParamsRequest>): String {
        jobService.startJobWithParams(jobName, params)
        return "Job Finished"
    }

    @GetMapping("/stop/{executionId}")
    fun stopJob(@PathVariable executionId: Long): String {
        jobService.stopJob(executionId)
        return "Job Stopped..."
    }
}