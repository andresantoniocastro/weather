package com.castro.andres.cnntakehome.ui.adapters

import android.support.v4.app.FragmentManager
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.castro.andres.cnntakehome.R
import com.castro.andres.cnntakehome.data.entities.WeatherForecast
import com.castro.andres.cnntakehome.ui.DetailFragment
import kotlinx.android.synthetic.main.other_day_list_item.view.*
import kotlinx.android.synthetic.main.today_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherListAdapter(private val supportFragmentManager: FragmentManager, private var forecasts : List<WeatherForecast> = listOf()) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {


    private fun getOnClickListener(forecast : WeatherForecast) : View.OnClickListener{
        return View.OnClickListener {
            val detailFragment = DetailFragment.getInstance(forecast)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, detailFragment).addToBackStack("detail").commit()
        }
    }

    fun update(newForecasts : List<WeatherForecast>)
    {
        // check to see if the list has been updated for reals
        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                // === checks to see if they are the same object
                return forecasts[oldItemPosition] ===  newForecasts[newItemPosition]
            }

            override fun getOldListSize(): Int {
                return forecasts.size
            }

            override fun getNewListSize(): Int {
                return newForecasts.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                // == in data classes checks to see if properties match (at least I think).
                return forecasts[oldItemPosition] == newForecasts[newItemPosition]
            }

        })

        forecasts = newForecasts

        result.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListAdapter.ViewHolder {

        return when (viewType) {
            0 -> getTodayViewHolder(parent)
            else -> getForecastViewHolder(parent)
        }


    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(forecasts[position])
        holder.itemView.setOnClickListener(getOnClickListener(forecasts[position]))
    }

    override fun getItemViewType(position: Int): Int {
        // Dont know what the default return is, but we are gonna use this to tell the
        // recylcer view to display something different for todays weather which is always the first item in the list
        return position
    }

    /**
     * Helper function inflate today's list item and return a TodayListView that uses that view
     */
    private fun getTodayViewHolder(parent: ViewGroup): TodayViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.today_list_item, parent, false)

        return TodayViewHolder(view)
    }

    /**
     * Helper function inflate the forecasts list item and return a ForecastViewHolder that uses that view
     */
    private fun getForecastViewHolder(parent: ViewGroup): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.other_day_list_item, parent, false)
        return ForecastViewHolder(view)
    }


    /**
     * This ViewHolder is responsible for setting up the smaller items for future days.
     */
    class ForecastViewHolder(private val view: View) : ViewHolder(view) {
        override fun bindItems(forecast: WeatherForecast) {
            val timeStamp = forecast.date
            if(timeStamp > 0)
            {
                view.otherDayText.text = getDayOfWeekFromTimeStamp(forecast.date)
            }else
            {
                view.otherDayText.text = view.context.getString(R.string.unknown)
            }
            view.otherDayDescriptionText.text = forecast.dayDescription
            val currentTemp = forecast.currentTemp
            if(currentTemp > Double.MIN_VALUE)
            {
                view.otherDayCurrentTempText.text = forecast.currentTemp.toString()

            }else
            {
                view.otherDayCurrentTempText.text = view.context.getString(R.string.not_available)
            }

            val minTemp = forecast.minTemp
            if(minTemp > Double.MIN_VALUE)
            {
                view.otherDayMinTempText.text = forecast.minTemp.toString()

            }else
            {

                view.otherDayMinTempText.text = view.context.getString(R.string.not_available)
            }
            view.otherDayIconImage.setImageResource(getSmallIcon(forecast.iconId))
        }

    }

    /**
     * This ViewHolder is responsible for setting up the bigger item for Today's weather
     */
    class TodayViewHolder(private val view: View) : ViewHolder(view) {
        override fun bindItems(forecast: WeatherForecast) {
            val timeStamp = forecast.date
            if(timeStamp>0)
            {
                view.todayDateText.text = getDayOfWeekFromTimeStamp(forecast.date) + ", " + getDateFromTimeStamp(forecast.date)
            }else
            {
                view.todayDateText.text = view.context.getString(R.string.unknown)
            }
            view.todayDescriptionText.text = forecast.dayDescription
            val currentTemp = forecast.currentTemp
            if(currentTemp > Double.MIN_VALUE)
            {
                view.todayCurrentTempText.text = forecast.currentTemp.toString()
            }else
            {
                view.todayCurrentTempText.text = view.context.getString(R.string.not_available)

            }

            val minTemp = forecast.minTemp
            if(minTemp > Double.MIN_VALUE){
                view.todayMinTempText.text = forecast.minTemp.toString()

            }else
            {
                view.todayMinTempText.text = view.context.getString(R.string.not_available)
            }

            view.todayImageView.setImageResource(getArt(forecast.iconId))
        }
    }

    /**
     * Both TodayViewHolder and ForecastViewHolder are the same type of object that wants to bind an item to a View.
     * SO we make this abstract class so that onBindViewHolder can usse them without knowing any details.
     */
    abstract class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        abstract fun bindItems(forecast : WeatherForecast)
    }

    companion object {

        // establish some utility methods to help the UI out. They need to be in the companion object so the vviewholders can see them

        /**
         * Given an icon ID return the correct small icon
         */
        fun getSmallIcon(iconID : String) : Int
        {
            return when(iconID){
                "01d", "01n" -> R.drawable.ic_clear
                "02d", "02n" -> R.drawable.ic_light_clouds
                "03d", "03n", "04d", "04n" -> R.drawable.ic_cloudy
                "09d", "09n" -> R.drawable.ic_light_rain
                "10d", "10n" -> R.drawable.ic_rain
                "11d", "11n" -> R.drawable.ic_storm
                "13d", "13n" -> R.drawable.ic_snow
                "50d", "50n" -> R.drawable.ic_fog
                else -> R.drawable.ic_snow

            }
        }

        /**
         * Given an icon ID return a large icon
         */
        fun getArt(iconID : String) : Int
        {
            return when(iconID){
                "01d", "01n" -> R.drawable.art_clear
                "02d", "02n" -> R.drawable.art_light_clouds
                "03d", "03n", "04d", "04n" -> R.drawable.art_clouds
                "09d", "09n" -> R.drawable.art_light_rain
                "10d", "10n" -> R.drawable.art_rain
                "11d", "11n" -> R.drawable.art_storm
                "13d", "13n" -> R.drawable.art_snow
                "50d", "50n" -> R.drawable.art_fog
                else -> R.drawable.art_snow

            }
        }

        fun getWindDirection(deg : Double) : String
        {
            return when(deg){
                in 348.75..360.0, in 0.0..11.25-> "N"
                in 11.25..33.75 -> "NNE"
                in 33.75..56.25 -> "NE"
                in 56.25..78.75 -> "ENE"
                in 56.25..78.75 -> "E"
                in 101.25..123.75 -> "ESE"
                in 123.75..146.25 -> "SE"
                in 146.25..168.75 -> "SSE"
                in 168.75..191.25 -> "S"
                in 191.25..213.75 -> "SSW"
                in 213.75..236.25 -> "SW"
                in 236.25..258.75 -> "WSW"
                in 258.75..281.25 -> "W"
                in 281.25..303.75 -> "WNW"
                in 303.75..326.25 -> "NW"
                in 326.25..348.75 -> "NNW"
                else -> "Unknown Direction"
            }
        }



        /**
         * Return a string matching the day of the week from the timestamp.
         */
        fun getDayOfWeekFromTimeStamp(timeStamp: Long) : String
        {
            val date = Date(timeStamp * 1000)

            val format = SimpleDateFormat("EEEE")

            val today = Date()

            val calendar = Calendar.getInstance()
            calendar.time = today
            calendar.add(Calendar.DATE, 1)
            val tomorrow = calendar.time

            val todayString = format.format(today)
            val tomorrowString = format.format(tomorrow)
            val dateString = format.format(date)

            return when (dateString) {
                todayString -> "Today"
                tomorrowString -> "Tomorrow"
                else -> dateString
            }


        }

        /**
         * Given a UTC timestamp get the day and month
         */
        fun getDateFromTimeStamp(timeStamp : Long) : String
        {
            val date = Date(timeStamp  * 1000)

            val format = SimpleDateFormat("MMMM dd")

            return format.format(date)

        }
    }
}