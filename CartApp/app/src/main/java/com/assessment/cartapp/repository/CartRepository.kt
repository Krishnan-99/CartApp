package com.assessment.cartapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.assessment.cartapp.model.CartItem
import com.assessment.cartapp.room.CartItemDao

class CartRepository {

    lateinit var cartListLiveData: LiveData<List<CartItem>>

    companion object {
        private var instance: CartRepository? = null

        @Synchronized
        fun getInstance(): CartRepository {
            if (instance == null)
                instance = CartRepository()

            return instance!!
        }
    }


    fun getAllService(wordDao: CartItemDao) {
         cartListLiveData = wordDao.getAllCartItem().asLiveData()
    }
}