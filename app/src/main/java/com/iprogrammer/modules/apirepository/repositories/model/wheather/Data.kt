package com.iprogrammer.modules.apirepository.repositories.model.wheather

data class Data(
    val clouds: Int,
    val datetime: String,
    val dewpt: Double,
    val dhi: Double,
    val dni: Double,
    val ghi: Double,
    val max_dhi: Double,
    val max_dni: Double,
    val max_ghi: Double,
    val max_temp: Double,
    val max_temp_ts: Int,
    val max_uv: Double,
    val max_wind_dir: Int,
    val max_wind_spd: Double,
    val max_wind_spd_ts: Int,
    val min_temp: Double,
    val min_temp_ts: Int,
    val precip: Double,
    val precip_gpm: Double,
    val pres: Double,
    val rh: Double,
    val slp: Double,
    val snow: Int,
    val snow_depth: Int,
    val solar_rad: Double,
    val t_dhi: Double,
    val t_dni: Double,
    val t_ghi: Double,
    val t_solar_rad: Double,
    val temp: Double,
    val ts: Int,
    val wind_dir: Int,
    val wind_gust_spd: Double,
    val wind_spd: Double
)