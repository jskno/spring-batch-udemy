package com.jskno.spring_batch_udemy.genericJobs.reader

import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component

@Component
class FirstItemReader: ItemReader<Int> {

    companion object {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    }

    var index = 0

    override fun read(): Int? {
        println("Inside Item Reader")
        var item: Int
        if (index < list.size) {
           item = list[index]
           index++
           return item
        }
        index = 0
        return null
    }
}