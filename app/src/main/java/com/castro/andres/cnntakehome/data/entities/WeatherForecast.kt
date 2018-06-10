package com.castro.andres.cnntakehome.data.entities

data class WeatherForecast(val city : String, // usually Atlanta
                           var date : Long, // actual timestamp from server
                           var currentTemp : Int, //  temp according to last request
                           var minTemp : Int, //  min temp accordint to last request
                           var dayDescription : String, // description of day according to last request (i.e. cloudy)
                           var humidity : Int, //  humidity according to last request
                           var wind : Int, // wind according to last request
                           var pressure : Int, // pressure according last request
                           var isCurrent : Boolean // this represents current weather and not a forecast in future
)