package com.assessment.cartapp.room

import androidx.room.TypeConverter
import com.assessment.cartapp.model.Employee
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class EmployeeConvertor {

    @TypeConverter
    fun fromEmployeeList(cartList: List<Employee?>?): String? {
        if (cartList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Employee?>?>() {}.type
        return gson.toJson(cartList, type)
    }

    @TypeConverter
    fun toEmployeeList(cartString: String?): List<Employee>? {
        if (cartString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Employee?>?>() {}.type
        return gson.fromJson<List<Employee>>(cartString, type)
    }
}