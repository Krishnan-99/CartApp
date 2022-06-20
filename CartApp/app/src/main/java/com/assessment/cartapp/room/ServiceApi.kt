// Retrofit interface
package com.assessment.cartapp.room

import com.assessment.cartapp.model.Resource
import com.assessment.cartapp.model.Employee
import com.assessment.cartapp.model.Service
import retrofit2.http.GET

interface ServiceApi {
    @GET("challenge-services")
    suspend fun getServices(): Resource<List<Service>>

    @GET("challenge-employees")
    suspend fun getEmployees(): Resource<List<Employee>>
}
