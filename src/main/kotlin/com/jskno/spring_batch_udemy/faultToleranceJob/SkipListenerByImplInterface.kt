package com.jskno.spring_batch_udemy.faultToleranceJob

import com.jskno.spring_batch_udemy.csvOrientedJob.CsvStudent
import com.jskno.spring_batch_udemy.jsonOrientedJob.JsonOutStudent
import org.springframework.batch.core.SkipListener
import org.springframework.batch.item.file.FlatFileParseException
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileWriter

@Component
class SkipListenerByImplInterface: SkipListener<CsvStudent, JsonOutStudent> {
    override fun onSkipInRead(t: Throwable) {
        if (t is FlatFileParseException) {
            createFile(
                "C:\\My_Folder\\jsknoGithubProjects\\spring-batch-udemy\\src\\main\\resources\\faultToleranceJob\\skipInRead.txt",
                t.input
            )

        }
    }

    override fun onSkipInWrite(item: JsonOutStudent, t: Throwable) {
        createFile(
            "C:\\My_Folder\\jsknoGithubProjects\\spring-batch-udemy\\src\\main\\resources\\faultToleranceJob\\skipInProcess.txt",
            item.toString())
    }

    override fun onSkipInProcess(item: CsvStudent, t: Throwable) {
        createFile(
            "C:\\My_Folder\\jsknoGithubProjects\\spring-batch-udemy\\src\\main\\resources\\faultToleranceJob\\skipInWrite.txt",
            item.toString())
    }

    fun createFile(filePath: String, data: String) {
        val fileWriter = FileWriter(File(filePath), true)
        fileWriter.use {
            it.write("$data \n")
        }
    }
}