package com.castro.andres.cnntakehome.data.parser

import com.castro.andres.cnntakehome.data.entities.WeatherForecast
import org.json.JSONObject

class QueryResponseToWeatherParser {

    companion object {
        fun parseCurrentJson(jsonString : String) : WeatherForecast
        {
            val rootJsonObject = JSONObject(jsonString)

            val weatherJSONObject = rootJsonObject.getJSONArray("weather").getJSONObject(0)
            val description = weatherJSONObject.getString("description")
            val iconId = weatherJSONObject.getString("icon")

            val cityName = rootJsonObject.getString("name")
            val utcTimeStamp = rootJsonObject.getLong("dt")

            val mainJsonObject = rootJsonObject.getJSONObject("main")

            val temp = mainJsonObject.getDouble("temp")
            val humidity = mainJsonObject.getDouble("humidity")
            val pressure = mainJsonObject.getDouble("pressure")
            val minTemp = mainJsonObject.getDouble("temp_min")

            val windJsonObject = rootJsonObject.getJSONObject("wind")
            val windSpeed = windJsonObject.getDouble("speed")
            val windDegrees = windJsonObject.getDouble("deg")

            // The most current weather is ALWAYS at the 0 position in the table

            return WeatherForecast(0, cityName, utcTimeStamp, temp, minTemp, description, iconId, humidity, windSpeed, windDegrees,  pressure, true)

        }

        fun parseForecastJson(jsonString: String) : ArrayList<WeatherForecast>
        {
            val rootJsonObject = JSONObject(jsonString)

            val cityName = rootJsonObject.getJSONObject("city").getString("name")


            val forecastList = rootJsonObject.getJSONArray("list")

            val resultArray = arrayListOf<WeatherForecast>()
            for(index in 0 until forecastList.length())
            {
                val jsonForecastObject = forecastList.getJSONObject(index)

                val utcTimeStamp = jsonForecastObject.getLong("dt")
                val mainJsonObject = jsonForecastObject.getJSONObject("main")
                val temp = mainJsonObject.getDouble("temp")
                val humidity = mainJsonObject.getDouble("humidity")
                val pressure = mainJsonObject.getDouble("pressure")
                val minTemp = mainJsonObject.getDouble("temp_min")

                val weatherJSONObject = jsonForecastObject.getJSONArray("weather").getJSONObject(0)
                val description = weatherJSONObject.getString("description")
                val iconId = weatherJSONObject.getString("icon")

                val windJsonObject = jsonForecastObject.getJSONObject("wind")
                val windSpeed = windJsonObject.getDouble("speed")
                val windDegrees = windJsonObject.getDouble("deg")
                // the 0 position is always the current weather the rest are the most latest forecasts
                val newItem=WeatherForecast( index + 1, cityName, utcTimeStamp, temp, minTemp, description, iconId, humidity, windSpeed, windDegrees, pressure, false)
                resultArray.add(newItem)
            }

            return resultArray
        }
    }
}