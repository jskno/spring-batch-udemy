package com.jskno.spring_batch_udemy.controller

data class StudentResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String
)