package com.iprogrammer.modules.apirepository.repositories.model.wheather

data class objWheatherData(
    val city_id: String,
    val city_name: String,
    val country_code: String,
    val `data`: List<Data>,
    val lat: Double,
    val lon: Double,
    val sources: List<String>,
    val state_code: String,
    val station_id: String,
    val timezone: String
)