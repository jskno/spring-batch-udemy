package com.jskno.spring_batch_udemy.genericJobs.writer

import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class FirstItemWriter: ItemWriter<Long> {

    override fun write(items: MutableList<out Long>) {
        println("Inside Item Writer")
        items.forEach { println(it) }
    }
}