package com.castro.andres.cnntakehome.data.entities

data class WeatherForecast(val city : String, // usually Atlanta
                           var date : Long, // actual timestamp from server
                           var currentTemp : Double, //  temp according to last request
                           var minTemp : Double, //  min temp accordint to last request
                           var dayDescription : String, // description of day according to last request (i.e. cloudy)
                           var iconId : String,
                           var humidity : Double, //  humidity according to last request
                           var wind : Double, // wind according to last request
                           var pressure : Double, // pressure according last request
                           var isCurrent : Boolean // this represents current weather and not a forecast in future)
)