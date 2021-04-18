package com.iprogrammer.modules.apirepository.repositories.apicallbacks.user.response
import com.iprogrammer.modules.apirepository.repositories.model.wheather.objWheatherData


interface IGetWheatherDataResponse {
    fun onSuccessResponse(
        response: objWheatherData?,
        isError: Boolean
    )
    fun onFailureResponse()
}