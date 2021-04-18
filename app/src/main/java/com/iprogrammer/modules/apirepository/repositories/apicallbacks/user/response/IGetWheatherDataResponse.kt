package com.iprogrammer.modules.apirepository.repositories.apicallbacks.user.response
import com.iprogrammer.modules.apirepository.repositories.model.wheather.objWheatherData
import com.iprogrammer.modules.apirepository.repositories.model.wheather.response.ObjWheatherDetails


interface IGetWheatherDataResponse {
    fun onSuccessResponse(
        response: ObjWheatherDetails?,
        isError: Boolean
    )
    fun onFailureResponse()
}