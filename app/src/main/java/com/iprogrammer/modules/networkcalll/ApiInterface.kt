package kotlincodes.com.retrofitwithkotlin.retrofit

import com.iprogrammer.modules.apirepository.repositories.model.wheather.objWheatherData
import com.iprogrammer.modules.apirepository.repositories.model.wheather.response.ObjWheatherDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("data/2.5/weather?")
    fun getWheatherData(@Query("q") cityName:String,
                        @Query("appid") key:String
                        ): Call<ObjWheatherDetails>
}