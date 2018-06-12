package com.castro.andres.cnntakehome.data.db.contracts

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.castro.andres.cnntakehome.network.RequestStatus
import com.castro.andres.cnntakehome.data.entities.ForecastQuery

@Dao
interface RestDAO {
    @Delete
    fun deleteRequest(rq : ForecastQuery)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequest(rq:ForecastQuery) : Long

    @Update
    fun updateRequest(rq : ForecastQuery)

    @Query("SELECT * FROM forecast_queries WHERE forecast_queries.id = :id")
    fun getQuery(id : Int) : ForecastQuery

    @Query("SELECT request_status FROM forecast_queries WHERE forecast_queries.id = :id")
    fun getQueryStatus(id : Int) : RequestStatus

    @Query("SELECT * FROM forecast_queries ORDER BY forecast_queries.request_time DESC LIMIT 1")
    fun getLastQuery() : LiveData<ForecastQuery>

    @Query("SELECT * FROM forecast_queries WHERE forecast_queries.request_status = 3 AND forecast_queries.request_type = 1 ORDER BY forecast_queries.request_time DESC LIMIT 1")
    fun getLastSuccessfulCurrentQuery() : LiveData<ForecastQuery>

    @Query("SELECT * FROM forecast_queries WHERE forecast_queries.request_status = 3 AND forecast_queries.request_type = 2 ORDER BY forecast_queries.request_time DESC LIMIT 1")
    fun getLastSuccessfulForecastQuery() : LiveData<ForecastQuery>

}