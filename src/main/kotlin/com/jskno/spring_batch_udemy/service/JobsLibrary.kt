package com.jskno.spring_batch_udemy.service

import org.springframework.batch.core.Job
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class JobsLibrary {

    @Autowired
    private lateinit var jobs: List<Job>

    val jobsMap = mutableMapOf<String, Job>()

    @PostConstruct
    fun initJobsMap() {
        jobs.map { jobsMap[it.name] = it }
    }
}