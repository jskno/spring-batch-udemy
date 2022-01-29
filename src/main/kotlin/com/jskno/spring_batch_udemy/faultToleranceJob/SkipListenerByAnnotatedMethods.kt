package com.jskno.spring_batch_udemy.faultToleranceJob

import com.jskno.spring_batch_udemy.csvOrientedJob.CsvStudent
import com.jskno.spring_batch_udemy.jsonOrientedJob.JsonOutStudent
import org.springframework.batch.core.annotation.OnSkipInProcess
import org.springframework.batch.core.annotation.OnSkipInRead
import org.springframework.batch.core.annotation.OnSkipInWrite
import org.springframework.batch.item.file.FlatFileParseException
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileWriter
import java.lang.NullPointerException

@Component
class SkipListenerByAnnotatedMethods {

    @OnSkipInRead
    fun onSkipInRead(th: Throwable) {
        if (th is FlatFileParseException) {
            createFile(
                "C:\\My_Folder\\jsknoGithubProjects\\spring-batch-udemy\\src\\main\\resources\\faultToleranceJob\\skipInRead.txt",
                th.input)
        }
    }

    fun createFile(filePath: String, data: String) {
        val fileWriter = FileWriter(File(filePath), true)
        fileWriter.use {
            it.write("$data \n")
        }
    }

    @OnSkipInProcess
    fun skipInProcess(student: CsvStudent, th: Throwable) {
//        if (th is NullPointerException) {
            createFile(
                "C:\\My_Folder\\jsknoGithubProjects\\spring-batch-udemy\\src\\main\\resources\\faultToleranceJob\\skipInProcess.txt",
                student.toString())
//        }
    }

    @OnSkipInWrite
    fun skipInWrite(student: JsonOutStudent, th: Throwable) {
        createFile(
            "C:\\My_Folder\\jsknoGithubProjects\\spring-batch-udemy\\src\\main\\resources\\faultToleranceJob\\skipInWrite.txt",
            student.toString())
    }
}