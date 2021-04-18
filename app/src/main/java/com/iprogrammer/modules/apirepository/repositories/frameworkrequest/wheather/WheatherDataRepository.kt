package com.iprogrammer.modules.apirepository.repositories.frameworkrequest.wheather
import com.iprogrammer.modules.apirepository.IWheatherDataRepository
import com.iprogrammer.modules.apirepository.repositories.apicallbacks.user.response.IGetWheatherDataResponse
import com.google.gson.Gson
import com.iprogrammer.modules.apirepository.repositories.model.wheather.objWheatherData
import com.iprogrammer.modules.apirepository.utils.BaseConstat
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object WheatherDataRepository : IWheatherDataRepository {

    lateinit var iGetWheatherDataResponse: IGetWheatherDataResponse
    private fun getStatData(startdate: String, endDate: String) {
        val call: Call<objWheatherData> = ApiClient.getClient.getWheatherData(startdate,endDate,BaseConstat.API_KEY)
        call.enqueue(object : Callback<objWheatherData> {

            override fun onResponse(
                call: Call<objWheatherData>,
                response: Response<objWheatherData>
            ) {
                var objUserResponse:objWheatherData = response.body()!!
                iGetWheatherDataResponse.onSuccessResponse(objUserResponse,false)
            }

            override fun onFailure(call: Call<objWheatherData>?, t: Throwable?) {
                iGetWheatherDataResponse.onFailureResponse()

            }

        })
    }

    override fun apiWheatherDataReq(
        wheatherDataResponseListener: IGetWheatherDataResponse,
        startdate: String,
        endDate: String
    ) {

        this.iGetWheatherDataResponse = wheatherDataResponseListener
        getStatData(startdate,endDate)

    }


    fun getDeserializeResponse(response: String): objWheatherData? {
        return Gson().fromJson(
            response,
                objWheatherData::class.java
        )
    }

}