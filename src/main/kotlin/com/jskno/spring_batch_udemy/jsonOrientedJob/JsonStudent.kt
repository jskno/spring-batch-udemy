package com.jskno.spring_batch_udemy.jsonOrientedJob

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class JsonStudent(
    var id: Long = 0L,
//    @JsonProperty("first_name")
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "")