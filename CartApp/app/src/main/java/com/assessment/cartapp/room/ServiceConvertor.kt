package com.assessment.cartapp.room

import androidx.room.TypeConverter
import com.assessment.cartapp.model.Service
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ServiceConvertor {

    @TypeConverter
    fun fromServiceList(cartList: List<Service?>?): String? {
        if (cartList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Service?>?>() {}.type
        return gson.toJson(cartList, type)
    }

    @TypeConverter
    fun toServiceList(cartString: String?): List<Service>? {
        if (cartString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Service?>?>() {}.type
        return gson.fromJson<List<Service>>(cartString, type)
    }
}