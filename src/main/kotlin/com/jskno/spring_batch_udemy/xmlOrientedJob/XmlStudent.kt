package com.jskno.spring_batch_udemy.xmlOrientedJob

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "student")
data class XmlStudent(
    var id: Long = 0L,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "")