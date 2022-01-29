package com.jskno.spring_batch_udemy.restOrientedJob

import com.jskno.spring_batch_udemy.controller.StudentResponse
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class StudentService {

    private var students: MutableList<StudentResponse>? = null

    fun restCallToGetStudents(): MutableList<StudentResponse> {
        val restTemplate = RestTemplate()

        val endpoint = URI.create("http://localhost:8089/api/v1/students")
        val request = RequestEntity<Any>(HttpMethod.GET, endpoint)
        val respType = object: ParameterizedTypeReference<MutableList<StudentResponse>>(){}
        val response = restTemplate.exchange(request, respType)
        return response.body ?: mutableListOf()
    }

    fun getStudent(id: Long, name: String): StudentResponse? {
        println("Id: $id, name: $name")
        if (students == null) students = restCallToGetStudents()

        if (students != null && students!!.isNotEmpty()) return students!!.removeAt(0)
        return null
    }
}