package com.jskno.spring_batch_udemy.genericJobs.listener

import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.stereotype.Component

@Component
class FirstStepListener: StepExecutionListener {

    override fun beforeStep(stepExecution: StepExecution) {
        println("Before Step ${stepExecution.stepName}")
        println("Job Execution Context ${stepExecution.jobExecution.executionContext}")
        println("Step Execution Context ${stepExecution.executionContext}")

        stepExecution.executionContext.put("sec", "A step variable")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        println("After Step ${stepExecution.stepName}")
        println("Job Execution Context ${stepExecution.jobExecution.executionContext}")
        println("Step Execution Context ${stepExecution.executionContext}")
        return null
    }
}