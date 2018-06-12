package com.castro.andres.cnntakehome.data.db.contracts

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.castro.andres.cnntakehome.network.RequestStatus
import com.castro.andres.cnntakehome.data.entities.ForecastQuery
import com.castro.andres.cnntakehome.data.entities.WeatherForecast

@Dao
interface RestDAO {
    @Delete
    fun deleteRequest(rq : ForecastQuery)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequest(rq:ForecastQuery) : Long

    @Update
    fun updateRequest(rq : ForecastQuery)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: WeatherForecast): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecasts(forecasts : List<WeatherForecast>)


    @Query("SELECT * FROM forecast_queries WHERE forecast_queries.id = :id")
    fun getQuery(id : Int) : ForecastQuery

    @Query("SELECT * FROM weather_forecasts WHERE weather_forecasts.id = :id")
    fun getWeather(id : Int) : WeatherForecast

    @Query("SELECT request_status FROM forecast_queries WHERE forecast_queries.id = :id")
    fun getQueryStatus(id : Int) : RequestStatus

    @Query("SELECT * FROM forecast_queries ORDER BY forecast_queries.request_time DESC LIMIT 1")
    fun getLastQuery() : LiveData<ForecastQuery>

    @Query("SELECT * FROM forecast_queries WHERE forecast_queries.request_status = 3 AND forecast_queries.request_type = 1 ORDER BY forecast_queries.request_time DESC LIMIT 1")
    fun getLastSuccessfulCurrentQuery() : LiveData<ForecastQuery>

    @Query("SELECT * FROM forecast_queries WHERE forecast_queries.request_status = 3 AND forecast_queries.request_type = 2 ORDER BY forecast_queries.request_time DESC LIMIT 1")
    fun getLastSuccessfulForecastQuery() : LiveData<ForecastQuery>

    // the 0 position is always the current weather (well if there are any forecasts)
    @Query("SELECT * FROM weather_forecasts WHERE weather_forecasts.id = 0")
    fun getLatestCurrentWeather() : LiveData<WeatherForecast>

    // get all the items in the forecasts tables except the one with id = 0
    @Query("SELECT * FROM weather_forecasts EXCEPT SELECT * FROM weather_forecasts WHERE weather_forecasts.id = 0")
    fun getLatestForecasts() : LiveData<List<WeatherForecast>>

    @Query("SELECT * FROM weather_forecasts")
    fun getForecasts() : LiveData<List<WeatherForecast>>

}