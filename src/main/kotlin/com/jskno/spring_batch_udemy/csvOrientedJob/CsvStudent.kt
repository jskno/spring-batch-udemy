package com.jskno.spring_batch_udemy.csvOrientedJob

data class CsvStudent(
    var id: Long = 0L,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "")