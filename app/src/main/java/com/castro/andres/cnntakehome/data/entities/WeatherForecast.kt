package com.castro.andres.cnntakehome.data.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weather_forecasts")
data class WeatherForecast(@PrimaryKey var id : Int,
                           val city : String, // usually Atlanta
                           var date : Long, // actual timestamp from server
                           @ColumnInfo(name = "current_temp") var currentTemp : Double, //  temp according to last request
                           @ColumnInfo(name = "min_temp") var minTemp : Double, //  min temp accordint to last request
                           @ColumnInfo(name = "day_description") var dayDescription : String, // description of day according to last request (i.e. cloudy)
                           @ColumnInfo(name = "icon_id") var iconId : String,
                           var humidity : Double, //  humidity according to last request
                           var wind : Double, // wind according to last request
                           @ColumnInfo(name= "wind_degree") var windDegree : Double,
                           var pressure : Double, // pressure according last request
                           @ColumnInfo(name = "is_current") var isCurrent : Boolean // this represents current weather and not a forecast in future)
)