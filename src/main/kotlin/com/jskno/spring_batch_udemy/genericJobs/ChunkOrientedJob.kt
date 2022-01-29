package com.jskno.spring_batch_udemy.genericJobs

import com.jskno.spring_batch_udemy.genericJobs.processor.FirstItemProcessor
import com.jskno.spring_batch_udemy.genericJobs.reader.FirstItemReader
import com.jskno.spring_batch_udemy.genericJobs.writer.FirstItemWriter
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChunkOrientedJob {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    private lateinit var firstItemReader: FirstItemReader

    @Autowired
    private lateinit var firstItemProcessor: FirstItemProcessor

    @Autowired
    private lateinit var firstItemWriter: FirstItemWriter

    @Bean
    fun secondJob(): Job {
        return jobBuilderFactory.get("SecondJob")
            // Must delete DDBB to make it work
            .incrementer(RunIdIncrementer())
            .start(firstStep())
            .next(chunkStep())
            .build()
    }

    private fun firstStep(): Step {
        return stepBuilderFactory.get("First Step")
            .tasklet(firstTask())
            .build()
    }

    private fun firstTask(): Tasklet {
        return Tasklet { contribution, chunkContext ->
            println("This is first tasklet step in Job2")
            RepeatStatus.FINISHED
        }
    }

    private fun chunkStep(): Step {
        return stepBuilderFactory.get("Second Step")
            .chunk<Int, Long>(3)
            .reader(firstItemReader)
            .processor(firstItemProcessor)
            .writer(firstItemWriter)
            .build()
    }

}