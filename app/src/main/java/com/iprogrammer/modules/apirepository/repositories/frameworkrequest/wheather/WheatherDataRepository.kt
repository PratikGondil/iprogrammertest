package com.iprogrammer.modules.apirepository.repositories.frameworkrequest.wheather
import com.iprogrammer.modules.apirepository.IWheatherDataRepository
import com.iprogrammer.modules.apirepository.repositories.apicallbacks.user.response.IGetWheatherDataResponse
import com.google.gson.Gson
import com.iprogrammer.modules.apirepository.repositories.model.wheather.response.ObjWheatherDetails
import com.iprogrammer.modules.apirepository.utils.BaseConstat
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object WheatherDataRepository : IWheatherDataRepository {

    lateinit var iGetWheatherDataResponse: IGetWheatherDataResponse
    private fun getWheatherData(cityName: String) {
        val call: Call<ObjWheatherDetails> = ApiClient.getClient.getWheatherData(cityName,
            BaseConstat.API_KEY)
        call.enqueue(object : Callback<ObjWheatherDetails> {

            override fun onResponse(
                call: Call<ObjWheatherDetails>,
                response: Response<ObjWheatherDetails>
            ) {
                var objUserResponse:ObjWheatherDetails = response.body()!!
                iGetWheatherDataResponse.onSuccessResponse(objUserResponse,false)
            }

            override fun onFailure(call: Call<ObjWheatherDetails>?, t: Throwable?) {
                iGetWheatherDataResponse.onFailureResponse()

            }

        })
    }


    override fun apiWheatherDataReq(
        wheatherDataResponseListener: IGetWheatherDataResponse,
        cityName: String
    ) {

        this.iGetWheatherDataResponse = wheatherDataResponseListener
        getWheatherData(cityName)

    }


    fun getDeserializeResponse(response: String): ObjWheatherDetails? {
        return Gson().fromJson(
            response,
            ObjWheatherDetails::class.java
        )
    }

}