package com.castro.andres.cnntakehome.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.castro.andres.cnntakehome.R
import com.castro.andres.cnntakehome.data.entities.WeatherForecast
import com.castro.andres.cnntakehome.ui.adapters.WeatherListAdapter

/**
 * This class is responsible for displaying the details panel to the user
 */
class DetailFragment : Fragment() {



    companion object {


        private const val city_key = "CITY"
        private const val date_key = "UTC"
        private const val temp_key = "CURRENT_TEMP"
        private const val min_temp_key = "MIN_TEMP"
        private const val description_key = "DESCRIPTION"
        private const val icon_key = "ICON_ID"
        private const val humidity_key = "HUMIDITY"
        private const val wind_key = "WIND"
        private const val pressure_key = "PRESSURE"
        private const val wind_deg_key = "WIND_DEG"

        fun getInstance(forecast : WeatherForecast) : DetailFragment
        {

            val frag = DetailFragment()
            val bundle = Bundle()
            bundle.putString(city_key, forecast.city)
            bundle.putLong(date_key, forecast.date)
            bundle.putDouble(temp_key, forecast.currentTemp)
            bundle.putDouble(min_temp_key, forecast.minTemp)
            bundle.putString(description_key, forecast.dayDescription)
            bundle.putString(icon_key, forecast.iconId)
            bundle.putDouble(humidity_key, forecast.humidity)
            bundle.putDouble(wind_key, forecast.wind)
            bundle.putDouble(pressure_key, forecast.pressure)
            bundle.putDouble(wind_deg_key, forecast.windDegree)
            frag.arguments = bundle

            return frag
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.detail_panel_layout, container, false)


        if(arguments != null)
        {

            val timeStamp = arguments!!.getLong(date_key)
            if(timeStamp > 0) {
                root.findViewById<TextView>(R.id.detailDayText).text = WeatherListAdapter.getDayOfWeekFromTimeStamp(arguments!!.getLong(date_key))
                root.findViewById<TextView>(R.id.detailDateText).text = WeatherListAdapter.getDateFromTimeStamp(arguments!!.getLong(date_key))
            }else
            {
                root.findViewById<TextView>(R.id.detailDayText).text = getString(R.string.unknown)
                root.findViewById<TextView>(R.id.detailDateText).text = getString(R.string.unknown)
            }

            val currentTemp = arguments!!.getDouble(temp_key)

            if(currentTemp > Double.MIN_VALUE)
            {
                root.findViewById<TextView>(R.id.detailCurrentTempText).text = currentTemp.toString()
            }else
            {
                root.findViewById<TextView>(R.id.detailCurrentTempText).text = getString(R.string.unknown)
            }


            val minTemp = arguments!!.getDouble(min_temp_key)
            if(minTemp > Double.MIN_VALUE)
            {
                root.findViewById<TextView>(R.id.detailMinTempText).text = minTemp.toString()
            }else
            {
                root.findViewById<TextView>(R.id.detailMinTempText).text = getString(R.string.unknown)
            }

            val humidity = arguments!!.getDouble(humidity_key)
            if(humidity > Double.MIN_VALUE)
            {
                root.findViewById<TextView>(R.id.detailHumidityText).text = humidity.toString()
                root.findViewById<TextView>(R.id.detailHumiditySuffix).visibility = View.VISIBLE
            }else
            {
                root.findViewById<TextView>(R.id.detailHumidityText).text = getString(R.string.unknown)
                root.findViewById<TextView>(R.id.detailHumiditySuffix).visibility = View.INVISIBLE
            }


            val pressure = arguments!!.getDouble(pressure_key)
            if(pressure > Double.MIN_VALUE)
            {
                root.findViewById<TextView>(R.id.detailPressureText).text = pressure.toString()
                root.findViewById<TextView>(R.id.detailPressureSuffix).visibility = View.VISIBLE
            }else
            {

                root.findViewById<TextView>(R.id.detailPressureText).text = getString(R.string.unknown)
                root.findViewById<TextView>(R.id.detailPressureSuffix).visibility = View.INVISIBLE
            }

            val windSpeed = arguments!!.getDouble(wind_key)
            val windDegrees = arguments!!.getDouble(wind_deg_key)
            if(windSpeed > Double.MIN_VALUE)
            {
                root.findViewById<TextView>(R.id.detailWindText).text = arguments!!.getDouble(wind_key).toString()
                root.findViewById<TextView>(R.id.detailWindText).visibility = View.VISIBLE

                if(windDegrees > Double.MIN_VALUE)
                {
                    root.findViewById<TextView>(R.id.detailWindDirection).visibility = View.VISIBLE
                    root.findViewById<TextView>(R.id.detailWindDirection).text = WeatherListAdapter.getWindDirection(arguments!!.getDouble(wind_deg_key))

                }else
                {
                    root.findViewById<TextView>(R.id.detailWindDirection).visibility = View.INVISIBLE
                }

            }else
            {
                root.findViewById<TextView>(R.id.detailWindText).visibility = View.INVISIBLE

            }


            root.findViewById<TextView>(R.id.detailDayDescriptionText).text = arguments!!.getString(description_key)
            root.findViewById<ImageView>(R.id.detailImageView).setImageResource(WeatherListAdapter.getArt(arguments!!.getString(icon_key)))
        }


        return root
    }
}