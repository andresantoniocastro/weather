package com.castro.andres.cnntakehome.data.parser

import android.util.Log
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

            return WeatherForecast(cityName, utcTimeStamp, temp, minTemp, description, iconId, humidity, windSpeed,  pressure, true)

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

                val newItem=WeatherForecast(cityName, utcTimeStamp, temp, minTemp, description, iconId, humidity, windSpeed,  pressure, false)
                resultArray.add(newItem)
            }

            return resultArray
        }
    }
}