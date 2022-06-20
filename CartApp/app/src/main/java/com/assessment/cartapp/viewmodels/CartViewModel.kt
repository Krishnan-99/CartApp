package com.assessment.cartapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.assessment.cartapp.model.CartItem
import com.assessment.cartapp.repository.CartRepository
import com.assessment.cartapp.room.CartItemDao

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private var cartRepository = CartRepository.getInstance()

    fun loadServices(wordDao: CartItemDao) {
        cartRepository.getAllService(wordDao)
    }

    fun getCartListData(): LiveData<List<CartItem>> {
        return cartRepository.cartListLiveData
    }
}