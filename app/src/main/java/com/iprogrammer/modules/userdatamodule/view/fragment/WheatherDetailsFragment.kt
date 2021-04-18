package com.iprogrammer.modules.userdatamodule.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iprogrammer.R
import com.iprogrammer.databinding.WheatherDataBinder
import com.iprogrammer.modules.apirepository.repositories.model.wheather.response.ObjWheatherDetails
import com.iprogrammer.modules.covidstatmodule.uiListner.IWheatherView
import com.iprogrammer.modules.userdatamodule.viewmodel.WheatherDataViewModel
import com.iprogrammer.modules.utils.BaseUtils
import com.iprogrammer.roomdatabase.dao.WheatherDao
import com.iprogrammer.roomdatabase.database.AppDatabase
import com.iprogrammer.roomdatabase.entity.Wheather
import java.sql.Time


class WheatherDetailsFragment : Fragment(), IWheatherView {
    companion object {
        fun newInstance() =
            WheatherDetailsFragment()
    }

    private lateinit var wheatherDataViewModel: WheatherDataViewModel
    lateinit var binder: WheatherDataBinder
    lateinit var Wheatherview: View
    private var database: AppDatabase? = null

    // lateinit var wheatherDao: WheatherDao
    lateinit var wheatherDao: WheatherDao

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
        binder.lifecycleOwner = this
        binder.wheatherDataViewModel = wheatherDataViewModel
        binder.wheatherDataViewModel!!.iWheatherView = this
        Wheatherview = binder.root
        initView()
        return Wheatherview
    }

    private fun initView() {
        database = AppDatabase.getAppDataBase(context!!)
        observerWheatherAPI()
        isItemIsPresentSpecificTime()
        autoCompleteTextViewFeature()
    }

    private fun autoCompleteTextViewFeature() {
        val cityList = getStoredCityList()
        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_list_item_1, cityList
        )
        binder.txtCityName.setAdapter(adapter)

        binder.txtCityName.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                getTheDataByCity(selectedItem)


            }
    }

    fun getTheDataByCity(selectedItem: String) {
        var wheatherList = retriviveWheatherData()
        for (whether in wheatherList) {
            if (whether.cityname.equals(selectedItem,ignoreCase = true)) {
                setWheatherData(whether)
            }
        }

    }

    private fun getStoredCityList(): List<String> {
        val cityList = ArrayList<String>()
        var wheatherList = retriviveWheatherData()
        for (whether in wheatherList) {
            cityList.add(whether.cityname!!)
        }
        return cityList

    }


    override fun callGetWheatherApi(cityName: String) {
        BaseUtils.hideSoftKeyBoard(context!!, view!!)
        var list = retriviveWheatherData()
        var isDataSet = false
        for (i in list) {
            if (i.cityname.equals(cityName, ignoreCase = true)) {
                if (isItemIsPresentSpecificTime()) {
                    isDataSet = true
                    setWheatherData(i)
                    break
                }
            }
        }
        if (!isDataSet) {
            BaseUtils.showProgress(activity!!)
            wheatherDataViewModel.callWheatherDataAPI(cityName)
        }

    }

    fun isItemIsPresentSpecificTime(): Boolean {
        var wheatherData = retriviveWheatherData()
        var time = ""
        var currentDateTime = BaseUtils.getCurrentDateTime()
        var diff: Time
        var currentTime = ""

        for (wheather in wheatherData) {
            time = wheather.date.split(" ")[1]
            currentTime = currentDateTime.split(" ")[1]
            var currenthour = currentTime.split(":")[0]
            var currentmin = currentTime.split(":")[1]
            var currentsec = currentTime.split(":")[2]
            var hour = time.split(":")[0]
            var min = time.split(":")[1]
            var sec = time.split(":")[2]
            val start = Time(currenthour.toInt(), currentmin.toInt(), currentsec.toInt())
            val stop = Time(hour.toInt(), min.toInt(), sec.toInt())
            diff = BaseUtils.difference(start, stop)

            if (diff.minutes >= 10) {
                clearDataFromDatabase(wheather)
                return false
            }


        }
        return true

    }


    private fun clearDataFromDatabase(wheatherData: Wheather) {
        wheatherDao = database?.wheatherDao()!!
        with(wheatherDao) {
            this?.deleteWheather(wheatherData)
        }
    }

    override fun searchWheatherData() {
        callGetWheatherApi(wheatherDataViewModel.cityName.get().toString())
    }

    fun observerWheatherAPI() {
        wheatherDataViewModel.responseobserver.observe(
            viewLifecycleOwner,
            Observer { objWheatherDetails ->
                BaseUtils.stopProgress()
                setData(objWheatherDetails)


            })
    }

    private fun setData(objdata: ObjWheatherDetails) {
        binder.rlView.visibility = View.VISIBLE
        wheatherDataViewModel.cityText.set(objdata.name.toString())
        wheatherDataViewModel.maxTemp.set(objdata.main.temp_max.toString())
        wheatherDataViewModel.minTemp.set(objdata.main.temp_min.toString())
        wheatherDataViewModel.day.set(BaseUtils.getCurrentDateTime())
        insertTheDataIntoDatabase(objdata)


    }

    fun setWheatherData(wheather: Wheather) {
        BaseUtils.hideSoftKeyBoard(context!!,view!!)
        binder.rlView.visibility =View.VISIBLE
        wheatherDataViewModel.cityText.set(wheather.cityname)
        wheatherDataViewModel.minTemp.set(wheather.mintemp)
        wheatherDataViewModel.maxTemp.set(wheather.maxtemp)
        wheatherDataViewModel.day.set(wheather.date)
    }


    private fun insertTheDataIntoDatabase(objWheatherRespone: ObjWheatherDetails) {
        val wheather = Wheather()

        val wheatherCopy = wheather.copy(
            cityname = objWheatherRespone.name,
            mintemp = objWheatherRespone.main.temp_min.toString(),
            maxtemp = objWheatherRespone.main.temp_min.toString(),
            date = BaseUtils.getCurrentDateTime()
        )

        insertWheatherData(wheatherCopy!!)


    }

    private fun retriviveWheatherData(): List<Wheather> {
        val list = database?.wheatherDao()?.getWheather()
        return list!!
    }


    fun insertWheatherData(wheather: Wheather) {
        wheatherDao = database?.wheatherDao()!!

        with(wheatherDao) {
            this?.insertWheather(wheather)
        }

    }
}