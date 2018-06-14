package com.castro.andres.cnntakehome.data.parser

import com.castro.andres.cnntakehome.data.entities.WeatherForecast
import org.json.JSONArray
import org.json.JSONObject

class QueryResponseToWeatherParser {

    companion object {
        fun parseCurrentJson(jsonString : String) : WeatherForecast
        {
            val rootJsonObject = JSONObject(jsonString)

            var description = "n/a"
            var iconId = "n/a"
            if(rootJsonObject.has("weather") && rootJsonObject.get("weather") is JSONArray) {
                val weatherJSONArray = rootJsonObject.getJSONArray("weather")
                if(weatherJSONArray.length() > 0)
                {
                    val weatherJSONObject = weatherJSONArray.getJSONObject(0)
                    description = weatherJSONObject.optString("description", "n/a")
                    iconId = weatherJSONObject.optString("icon", "n/a")
                }
            }

            val cityName = rootJsonObject.optString("name", "n/a")
            val utcTimeStamp = rootJsonObject.optLong("dt", Long.MIN_VALUE)


            var temp = Double.MIN_VALUE
            var humidity = Double.MIN_VALUE
            var pressure = Double.MIN_VALUE
            var minTemp = Double.MIN_VALUE

            if(rootJsonObject.has("main"))
            {
                val mainJSONObject = rootJsonObject.getJSONObject("main")
                temp = mainJSONObject.optDouble("temp", Double.MIN_VALUE)
                humidity = mainJSONObject.optDouble("humidity", Double.MIN_VALUE)
                pressure = mainJSONObject.optDouble("pressure", Double.MIN_VALUE)
                minTemp = mainJSONObject.optDouble("temp_min", Double.MIN_VALUE)

            }

            var windSpeed = Double.MIN_VALUE
            var windDegrees = Double.MIN_VALUE
            if(rootJsonObject.has("wind"))
            {
                val windJsonObject = rootJsonObject.getJSONObject("wind")
                windSpeed = windJsonObject.optDouble("speed", Double.MIN_VALUE)
                windDegrees = windJsonObject.optDouble("deg", Double.MIN_VALUE)
            }

            // The most current weather is ALWAYS at the 0 position in the table

            return WeatherForecast(0, cityName, utcTimeStamp, temp, minTemp, description, iconId, humidity, windSpeed, windDegrees,  pressure, true)

        }

        fun parseForecastJson(jsonString: String) : ArrayList<WeatherForecast>
        {
            val rootJsonObject = JSONObject(jsonString)

            var cityName = "n/a"
            if(rootJsonObject.has("city"))
            {
                cityName = rootJsonObject.getJSONObject("city").optString("name", "n/a")
            }

            val resultArray = arrayListOf<WeatherForecast>()

            if(rootJsonObject.has("list"))
            {
                val forecastList = rootJsonObject.getJSONArray("list")

                for(index in 0 until forecastList.length())
                {
                    val jsonForecastObject = forecastList.getJSONObject(index)

                    val utcTimeStamp = jsonForecastObject.optLong("dt", Long.MIN_VALUE)

                    var temp = Double.MIN_VALUE
                    var humidity = Double.MIN_VALUE
                    var pressure = Double.MIN_VALUE
                    var minTemp = Double.MIN_VALUE

                    if(jsonForecastObject.has("main"))
                    {
                        val mainJSONObject = jsonForecastObject.getJSONObject("main")
                        temp = mainJSONObject.optDouble("temp", Double.MIN_VALUE)
                        humidity = mainJSONObject.optDouble("humidity", Double.MIN_VALUE)
                        pressure = mainJSONObject.optDouble("pressure", Double.MIN_VALUE)
                        minTemp = mainJSONObject.optDouble("temp_min", Double.MIN_VALUE)
                    }


                    var description = "n/a"
                    var iconId = "n/a"
                    if(jsonForecastObject.has("weather") && jsonForecastObject.get("weather") is JSONArray) {
                        val weatherJSONArray = jsonForecastObject.getJSONArray("weather")
                        if(weatherJSONArray.length() > 0)
                        {
                            val weatherJSONObject = weatherJSONArray.getJSONObject(0)
                            description = weatherJSONObject.optString("description", "n/a")
                            iconId = weatherJSONObject.optString("icon", "n/a")
                        }
                    }

                    var windSpeed = Double.MIN_VALUE
                    var windDegrees = Double.MIN_VALUE
                    if(jsonForecastObject.has("wind"))
                    {
                        val windJsonObject = jsonForecastObject.getJSONObject("wind")
                        windSpeed = windJsonObject.optDouble("speed", Double.MIN_VALUE)
                        windDegrees = windJsonObject.optDouble("deg", Double.MIN_VALUE)
                    }
                    // the 0 position is always the current weather the rest are the most latest forecasts
                    val newItem=WeatherForecast( index + 1, cityName, utcTimeStamp, temp, minTemp, description, iconId, humidity, windSpeed, windDegrees, pressure, false)
                    resultArray.add(newItem)
                }

            }

            return resultArray
        }
    }
}