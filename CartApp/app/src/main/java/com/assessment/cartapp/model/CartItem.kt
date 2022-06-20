package com.assessment.cartapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item_table")
data class CartItem(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val employees: MutableList<Employee>,
    val service: MutableList<Service>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        TODO("employees"),
        TODO("service")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartItem> {
        override fun createFromParcel(parcel: Parcel): CartItem {
            return CartItem(parcel)
        }

        override fun newArray(size: Int): Array<CartItem?> {
            return arrayOfNulls(size)
        }
    }


}
