package com.jskno.spring_batch_udemy.genericJobs

import com.jskno.spring_batch_udemy.genericJobs.listener.FirstStepListener
import com.jskno.spring_batch_udemy.genericJobs.listener.FistJobListener
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBatchProcessing
class TaskletOrientedJob {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    private lateinit var secondTasklet: Tasklet

    @Autowired
    private lateinit var firstJobListener: FistJobListener

    @Autowired
    private lateinit var firstStepListener: FirstStepListener

    @Bean
    fun firstJob(): Job {
        return jobBuilderFactory.get("FirstJob")
            .incrementer(RunIdIncrementer())
            .start(firstStep())
            .next(secondStep())
            .listener(firstJobListener)
            .build()
    }

    private fun firstStep(): Step {
        return stepBuilderFactory.get("First Step")
            .tasklet(firstTask())
            .listener(firstStepListener)
            .build()
    }

    private fun firstTask(): Tasklet {
        return Tasklet { contribution, chunkContext ->
            println("This is first tasklet step in Job1")
            println("SEC = ${chunkContext.stepContext.stepExecutionContext["sec"]} ")
            RepeatStatus.FINISHED
        }
    }

    private fun secondStep(): Step {
        return stepBuilderFactory.get("Second Step")
            .tasklet(secondTasklet)
            .build()
    }

//    private fun secondTask(): Tasklet {
//        return Tasklet { contribution, chunkContext ->
//            println("This is second tasklet step")
//            RepeatStatus.FINISHED
//        }
//    }


}