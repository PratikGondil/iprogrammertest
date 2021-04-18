package kotlincodes.com.retrofitwithkotlin.retrofit

import com.iprogrammer.modules.apirepository.repositories.model.wheather.objWheatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2.0/history/daily?postal_code=27601&country=US&")
    fun getWheatherData(@Query("start_date") startdate:String,
                        @Query("end_date") enddate:String,
                        @Query("key") key:String
                        ): Call<objWheatherData>
}