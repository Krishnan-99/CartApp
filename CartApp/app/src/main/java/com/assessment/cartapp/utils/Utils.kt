package com.assessment.cartapp.utils

import android.R
import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class Utils {
    companion object {
        fun showToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun showSnack(activity: Activity, message: String) {
            val snackBar = Snackbar.make(
                activity.findViewById(R.id.content),
                message, BaseTransientBottomBar.LENGTH_LONG
            )
            snackBar.show()
        }
    }
}