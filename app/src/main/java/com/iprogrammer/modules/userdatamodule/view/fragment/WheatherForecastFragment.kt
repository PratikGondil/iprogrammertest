package com.iprogrammer.modules.userdatamodule.view.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iprogrammer.R
import com.iprogrammer.databinding.WheatherDataBinder
import com.iprogrammer.modules.apirepository.repositories.model.wheather.objWheatherData
import com.iprogrammer.modules.covidstatmodule.uiListner.IUserDataView
import com.iprogrammer.modules.userdatamodule.viewmodel.WheatherDataViewModel
import com.iprogrammer.modules.utils.BaseUtils
import java.text.SimpleDateFormat
import java.util.*


class WheatherForecastFragment : Fragment(), IUserDataView {
    companion object {
        fun newInstance() =
            WheatherForecastFragment()
    }

    private lateinit var wheatherDataViewModel: WheatherDataViewModel
    lateinit var binder: WheatherDataBinder
    lateinit var covidStatView: View
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    var cal = Calendar.getInstance()
    var endateCal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wheatherDataViewModel = ViewModelProviders.of(this).get(WheatherDataViewModel::class.java)
        binder =
            DataBindingUtil.inflate(
                inflater,
                R.layout.main_fragment,
                container,
                false
            )
        binder.setLifecycleOwner(this)
        binder.wheatherDataViewModel = wheatherDataViewModel
        wheatherDataViewModel.IUserDataView = this
        covidStatView = binder.root
        initView()
        return covidStatView
    }

    private fun initView() {
        observerWheatherAPI()
        addclickListner()
    }

    private fun addclickListner() {
        dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val nextDay = dayOfMonth + 1
                val month = monthOfYear
                endateCal.set(Calendar.DAY_OF_MONTH, nextDay)
                endateCal.set(Calendar.MONTH, month)
                endateCal.set(Calendar.YEAR, year)
                var isPrime = checkThePrimeDate(dayOfMonth + monthOfYear + year)
                val myFormat = "yyyy-MM-dd" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                binder.txtSelectedDate.text = sdf.format(cal.time)
                var nextDate = sdf.format(endateCal.time)
                if (!isPrime) {
                    callGetWheatherApi(nextDate)
                }else{
                    showdialoag()
                }
            }
    }

    private fun showdialoag() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.dialogTitle)
        builder.setMessage(R.string.dialogMessage)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    override fun callGetWheatherApi(endDate: String) {
        BaseUtils.showProgress(activity!!)
        wheatherDataViewModel.callWheatherDataAPI(
            binder.txtSelectedDate.text.toString().trim(),
            endDate
        )

    }

    override fun callDatePicker() {
        DatePickerDialog(
            activity!!, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun observerWheatherAPI() {
        wheatherDataViewModel.responseobserver.observe(
            viewLifecycleOwner,
            Observer { objStat ->
                BaseUtils.stopProgress()
                setData(objStat)


            })
    }

    private fun setData(objdata: objWheatherData) {
        binder.rlView.visibility = View.VISIBLE
        for (data in objdata.data) {
            wheatherDataViewModel.temp.set(data.temp.toString())
            wheatherDataViewModel.maxTemp.set(data.max_temp.toString())
            wheatherDataViewModel.minTemp.set(data.min_temp.toString())
            wheatherDataViewModel.day.set(data.datetime.toString())
        }


    }


    fun checkThePrimeDate(date: Int):Boolean {
        var isPrime: Boolean = false
        for (i in 2..date / 2) {
            if (date % i == 0) {
                isPrime = true
                break
            }
        }
        return isPrime
    }


}