package com.jskno.spring_batch_udemy.jsonOrientedJob

data class JsonOutStudent(
    var id: Long = 0L,
    var nombre: String = "",
    var appellido: String = "",
    var email: String = ""
)