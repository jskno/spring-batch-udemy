package com.jskno.spring_batch_udemy.genericJobs.tasklet

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Service

@Service
class SecondTasklet : Tasklet {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println("This is second tasklet step in Job1")
        println("This is job execution context: ${chunkContext.stepContext.jobExecutionContext}")
        return RepeatStatus.FINISHED
    }
}