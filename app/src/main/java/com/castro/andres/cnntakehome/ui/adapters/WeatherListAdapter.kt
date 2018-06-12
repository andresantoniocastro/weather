package com.castro.andres.cnntakehome.ui.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.castro.andres.cnntakehome.R
import com.castro.andres.cnntakehome.data.entities.WeatherForecast
import kotlinx.android.synthetic.main.other_day_list_item.view.*
import kotlinx.android.synthetic.main.today_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherListAdapter(/*private var forecasts : List<WeatherForecast> = listOf()*/) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    private var forecasts = listOf<WeatherForecast>(
//            WeatherForecast("Atlanta", 1528598441, 56, 1, "cloudy", 10, 0, 12, true ),
//            WeatherForecast("Atlanta", 1528598441, 99, 1, "cloudy", 10, 0, 12, true ),
//            WeatherForecast("Atlanta", 1528598441, 123, 1, "cloudy", 10, 0, 12, true ),
//            WeatherForecast("Atlanta", 1528598441, 213, 1, "cloudy", 10, 0, 12, true ),
//            WeatherForecast("Atlanta", 1528598441, 12, 1, "cloudy", 10, 0, 12, true )


    )

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
            view.otherDayText.text = getDayOfWeekFromTimeStamp(forecast.date)
            view.otherDayDescriptionText.text = forecast.dayDescription
            view.otherDayCurrentTempText.text = forecast.currentTemp.toString()
            view.otherDayMinTempText.text = forecast.minTemp.toString()
            view.otherDayIconImage.setImageResource(getSmallIcon(forecast.dayDescription))
        }

    }

    /**
     * This ViewHolder is responsible for setting up the bigger item for Today's weather
     */
    class TodayViewHolder(private val view: View) : ViewHolder(view) {
        override fun bindItems(forecast: WeatherForecast) {
            view.todayDateText.text = getDayOfWeekFromTimeStamp(forecast.date) + ", " + getDateFromTimeStamp(forecast.date)
            view.todayDescriptionText.text = forecast.dayDescription
            view.todayCurrentTempText.text = forecast.currentTemp.toString()
            view.todayMinTempText.text = forecast.minTemp.toString()
            view.todayImageView.setImageResource(getArt(forecast.dayDescription))
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
        private fun getSmallIcon(iconID : String) : Int
        {
            return when(iconID){
                "01d", "01n" -> R.drawable.ic_clear
                "02d", "02n" -> R.drawable.ic_light_clouds
                "03d", "03n", "04d", "04n" -> R.drawable.ic_cloudy
                "09d", "09n" -> R.drawable.ic_light_rain
                "10d", "10n" -> R.drawable.ic_rain
                "11d", "11n" -> R.drawable.ic_storm
                "13d", "13n" -> R.drawable.ic_snow
                "50d", "05n" -> R.drawable.ic_fog
                else -> R.drawable.ic_snow

            }
        }

        /**
         * Given an icon ID return a large icon
         */
        private fun getArt(iconID : String) : Int
        {
            return when(iconID){
                "01d", "01n" -> R.drawable.art_clear
                "02d", "02n" -> R.drawable.art_light_clouds
                "03d", "03n", "04d", "04n" -> R.drawable.art_clouds
                "09d", "09n" -> R.drawable.art_light_rain
                "10d", "10n" -> R.drawable.art_rain
                "11d", "11n" -> R.drawable.art_storm
                "13d", "13n" -> R.drawable.art_snow
                "50d", "05n" -> R.drawable.art_fog
                else -> R.drawable.art_snow

            }
        }



        /**
         * Return a string matching the day of the week from the timestamp.
         */
        private fun getDayOfWeekFromTimeStamp(timeStamp: Long) : String
        {
            val date = Date(timeStamp)

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
        private fun getDateFromTimeStamp(timeStamp : Long) : String
        {
            val date = Date(timeStamp)

            val format = SimpleDateFormat("MMMM dd")

            return format.format(date)

        }
    }
}