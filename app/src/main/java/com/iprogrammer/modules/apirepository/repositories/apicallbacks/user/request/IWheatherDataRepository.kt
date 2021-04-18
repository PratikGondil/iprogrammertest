package com.iprogrammer.modules.apirepository
import com.iprogrammer.modules.apirepository.repositories.apicallbacks.user.response.IGetWheatherDataResponse


/*
* Temporary Callback
 */
interface IWheatherDataRepository {

    fun apiWheatherDataReq(
        wheatherDataResponseListener: IGetWheatherDataResponse,
        date: String
    )



}