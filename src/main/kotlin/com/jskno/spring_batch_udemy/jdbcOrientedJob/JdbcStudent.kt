package com.jskno.spring_batch_udemy.jdbcOrientedJob

data class JdbcStudent(
    var id: Long = 0L,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null)