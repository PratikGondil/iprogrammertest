package com.iprogrammer.modules.covidstatmodule.uiListner

interface IWheatherView {
    fun callGetWheatherApi(nextDate: String)
    fun searchWheatherData()
}