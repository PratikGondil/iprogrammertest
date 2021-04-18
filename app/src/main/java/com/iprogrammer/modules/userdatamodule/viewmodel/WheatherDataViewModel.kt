package com.iprogrammer.modules.userdatamodule.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iprogrammer.modules.apirepository.repositories.apicallbacks.user.response.IGetWheatherDataResponse
import com.iprogrammer.modules.apirepository.repositories.frameworkrequest.wheather.WheatherDataRepository
import com.iprogrammer.modules.apirepository.repositories.model.wheather.objWheatherData
import com.iprogrammer.modules.apirepository.repositories.model.wheather.response.ObjWheatherDetails
import com.iprogrammer.modules.covidstatmodule.uiListner.IWheatherDataHandler
import com.iprogrammer.modules.covidstatmodule.uiListner.IWheatherView

class WheatherDataViewModel(application: Application) : AndroidViewModel(application), IWheatherDataHandler, IGetWheatherDataResponse {
    lateinit var iWheatherView: IWheatherView
    var responseobserver: MutableLiveData<ObjWheatherDetails> = MutableLiveData()
    var temp = ObservableField<String>()
    var cityText = ObservableField<String>()
    var minTemp = ObservableField<String>()
    var cityName = ObservableField<String>()
    var maxTemp = ObservableField<String>()
    var day = ObservableField<String>()
    var isEnable = ObservableField<Boolean>(false)


    override fun callWheatherDataAPI(cityName: String) {
        WheatherDataRepository.apiWheatherDataReq(this, cityName)
    }

    override fun onSuccessResponse(response: ObjWheatherDetails?, isError: Boolean) {
        responseobserver.value = response
    }

    override fun onFailureResponse() {


    }

    fun searchWheatherData()
    {
        iWheatherView.searchWheatherData()
    }


}