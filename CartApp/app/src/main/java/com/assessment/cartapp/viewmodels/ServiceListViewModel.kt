package com.assessment.cartapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.assessment.cartapp.model.Resource
import com.assessment.cartapp.model.Service
import com.assessment.cartapp.repository.ServiceRepository

class ServiceListViewModel(application: Application) : AndroidViewModel(application) {

    private var serviceRepository = ServiceRepository.getInstance()

    fun loadServices(){
        serviceRepository.getAllService()
    }

    fun getServiceListData(): LiveData<Resource<List<Service>>> {
        return serviceRepository.servicesListData
    }
}