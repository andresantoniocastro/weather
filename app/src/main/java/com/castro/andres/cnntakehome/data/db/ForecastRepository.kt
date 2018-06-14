package com.castro.andres.cnntakehome.data.db

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import com.castro.andres.cnntakehome.data.db.contracts.RestDAO
import com.castro.andres.cnntakehome.data.entities.ForecastQuery
import com.castro.andres.cnntakehome.data.entities.WeatherForecast

class ForecastRepository internal constructor(app : Application){

    private val db = ForecastDatabase.getInstance(app)

    // getInstance will build a new one so we can safely ignore this null check
    val restDao = db!!.getRestDao()
    var latestForecastQuery = restDao.getLastQuery()

    fun insertNewRequest(req : ForecastQuery){

        InsertAsyncTask(restDao).execute(req)
    }

    fun updateRequest(req : ForecastQuery)
    {
        UpdateAsyncTask(restDao).execute(req)
    }

    fun deleteRequest(req: ForecastQuery)
    {
        DeleteAsyncTask(restDao).execute(req)
    }

    fun insertWeatherForecast(forecast : WeatherForecast)
    {
        InsertWeatherAsyncTask(restDao).execute(forecast)
    }

    fun insertWeatherForecasts(forecasts : List<WeatherForecast>)
    {
        InsertMultipleForecastsAsyncTask(restDao).execute(forecasts)
    }

    private class InsertWeatherAsyncTask internal constructor(private val mAsyncTaskDao: RestDAO) : AsyncTask<WeatherForecast, Void, Void>() {

        override fun doInBackground(vararg params: WeatherForecast): Void? {
            Log.i(TAG, "insert forecasts: " + params[0])
            try{

                mAsyncTaskDao.insertForecast(params[0])
            }catch(e : Exception)
            {
                Log.e(TAG , "can't see db", e)
            }
            return null
        }

    }

    private class InsertMultipleForecastsAsyncTask internal constructor(private val mAsyncTaskDao: RestDAO) : AsyncTask<List<WeatherForecast>, Void, Void>() {

        override fun doInBackground(vararg params: List<WeatherForecast>): Void? {
            Log.i(TAG, "insert multiple forecasts:\n ")
            for(forecast in params[0])
            {
                Log.i(TAG, forecast.toString())
            }
            try{
                mAsyncTaskDao.insertForecasts(params[0])
            }catch (e : Exception)
            {
                Log.e(TAG , "can't see db", e)
            }
            return null
        }

    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: RestDAO) : AsyncTask<ForecastQuery, Void, Void>() {

        override fun doInBackground(vararg params: ForecastQuery): Void? {
            Log.i(TAG, "inserting query: " + params[0])
            try{

                mAsyncTaskDao.insertRequest(params[0])
            }catch (e : Exception)
            {
                Log.e(TAG , "can't see db", e)
            }
            return null
        }
    }

    private class UpdateAsyncTask internal constructor(private val mAsyncTaskDao: RestDAO) : AsyncTask<ForecastQuery, Void, Void>() {

        override fun doInBackground(vararg params: ForecastQuery): Void? {
            Log.i(TAG, "updating : " + params[0])
            try{

                mAsyncTaskDao.updateRequest(params[0])
            }catch(e : Exception)
            {
                Log.e(TAG , "can't see db", e)
            }
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val mAsyncTaskDao: RestDAO) : AsyncTask<ForecastQuery, Void, Void>() {

        override fun doInBackground(vararg params: ForecastQuery): Void? {
            mAsyncTaskDao.deleteRequest(params[0])
            return null
        }
    }

    companion object {
        private const val TAG = "ForecastRepository"
    }
}