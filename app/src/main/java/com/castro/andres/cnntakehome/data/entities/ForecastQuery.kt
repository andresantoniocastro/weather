package com.castro.andres.cnntakehome.data.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.castro.andres.cnntakehome.network.RequestStatus
import com.castro.andres.cnntakehome.network.RequestType


/**
 * POJO responsible for holding a REST query in the db.
 *
 * The DB is aware of the last time we made a request to the server, the current status of that request, and the resulting data of that request.
 * If this was a production app we would have to worry about clearing the DB occasionally, but not for this demo app.
 * Also if we needed to worry about PUT requests we could add another column with the type of request.
 */

@Entity(tableName="forecast_queries")
data class ForecastQuery(
                         @ColumnInfo(name = "request_time") var attemptTime : Long, //unix timestamp of when the query was attempted\
                         @ColumnInfo(name = "request_type") var openWeatherRequestType : RequestType, // type of request of openweather (current or forecast)
                         @ColumnInfo(name = "request_status") var currentStatus : RequestStatus, // current state of this query
                         @ColumnInfo(name = "request") var requestText : String,
                         @ColumnInfo(name = "request_data") var data : String = "") // resulting data from this query starts off empty
{
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null


    constructor(): this(0L, RequestType.UNKNOWN, RequestStatus.NO_CONNECTION, "", "")
}
