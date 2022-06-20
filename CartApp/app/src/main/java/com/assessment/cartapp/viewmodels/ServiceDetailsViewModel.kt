package com.assessment.cartapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.assessment.cartapp.model.Resource
import com.assessment.cartapp.model.CartItem
import com.assessment.cartapp.model.Employee
import com.assessment.cartapp.repository.ServiceDetailRepository
import com.assessment.cartapp.room.CartItemDao
import kotlinx.coroutines.launch

class ServiceDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private var serviceDetailRepository = ServiceDetailRepository.getInstance()

    fun getAllEmployees() {
        serviceDetailRepository.getAllEmployees()
    }

    fun getEmployeesListData(): LiveData<Resource<List<Employee>>> {
        return serviceDetailRepository.employeesListData
    }

     fun insertCartItem(cartItemDao: CartItemDao, cartItem: CartItem) {
         viewModelScope.launch {
             serviceDetailRepository.insertCartItem(cartItemDao, cartItem)
         }
    }

    fun updateCartItem(cartItemDao: com.assessment.cartapp.room.CartItemDao, cartItem: CartItem) {
        viewModelScope.launch {
            serviceDetailRepository.updateCartItem(cartItemDao, cartItem)
        }
    }
}