package com.iprogrammer.modules.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

object BaseUtils {

    lateinit var progressDialog: ProgressDialog
    fun showProgress(activity :Activity) {
        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()
    }

    fun stopProgress() {
     progressDialog.hide()
    }

    fun getCurrentDateTime() :String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate.toString()
    }

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }

    fun difference(start: Time, stop: Time): Time {
        val diff = Time(0, 0, 0)

        if (stop.seconds > start.seconds) {
            --start.minutes
            start.seconds += 60
        }

        diff.seconds = start.seconds - stop.seconds
        if (stop.minutes > start.minutes) {
            --start.hours
            start.minutes += 60
        }

        diff.minutes = start.minutes - stop.minutes
        diff.hours = start.hours - stop.hours

        return diff
    }
}