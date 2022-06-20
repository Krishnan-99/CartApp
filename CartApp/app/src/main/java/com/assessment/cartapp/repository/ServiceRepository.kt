package com.assessment.cartapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assessment.cartapp.retrofit.RetrofitHelper
import com.assessment.cartapp.room.ServiceApi
import com.assessment.cartapp.model.Resource
import com.assessment.cartapp.model.Service
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ServiceRepository {

    private val _servicesListData = MutableLiveData<Resource<List<Service>>>(null)
    val servicesListData: LiveData<Resource<List<Service>>>
        get() = _servicesListData

    companion object {
        private var instance: ServiceRepository? = null

        @Synchronized
        fun getInstance(): ServiceRepository {
            if (instance == null)
                instance = ServiceRepository()

            return instance!!
        }
    }


    fun getAllService() {
        _servicesListData.postValue(Resource.loading(emptyList()))
        val serviceApi = RetrofitHelper.getInstance().create(ServiceApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = serviceApi.getServices()
            if (result.responseCode == 200) {
                Resource.success(result.data)?.let { _servicesListData.postValue(it) }
            } else {
                _servicesListData.postValue(Resource.error("Error", emptyList()))
            }

        }
    }
}