package com.castro.andres.cnntakehome.network

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import com.castro.andres.cnntakehome.data.db.ForecastRepository
import com.castro.andres.cnntakehome.data.entities.ForecastQuery
import com.castro.andres.cnntakehome.data.parser.QueryResponseToWeatherParser
import org.json.JSONException
import java.net.SocketTimeoutException

class RESTAsyncTask(private val app: Application, private val forecast: ForecastQuery) : AsyncTask<ForecastQuery, Void, String>(){


    override fun onPreExecute() {
        super.onPreExecute()

        // update current query to say that we are talking to the server
        forecast.currentStatus = RequestStatus.IN_TRANSIT
        ForecastRepository(app).updateRequest(forecast)
    }

    override fun doInBackground(vararg params: ForecastQuery?): String {

        Thread.currentThread().name = "cnn thread"
        // do the actual guts of talking to the server
        var result = ""
        try{

            result = RESTMethod.get(forecast.requestText)

            // if it wasn't successful then update the db as having an issue
        }catch (re : RuntimeException)
        {
            forecast.currentStatus = RequestStatus.ERROR
            result = re.message ?: RESTMethod.ERROR_STRING
        }catch(sse : SocketTimeoutException) {
            forecast.currentStatus = RequestStatus.ERROR
            result = sse.message ?: RESTMethod.ERROR_STRING
        }

        Log.d("MAKO", "result of REST was $result")
        return result

    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        Log.d("MAKO", result)
        // if something went wrong other than the bad response code deal with it ow
        if(result == null || result == RESTMethod.ERROR_STRING || result == "")
        {
            forecast.currentStatus = RequestStatus.ERROR
            // if there was no error than go ahead and update the status to be a success
        }else if(forecast.currentStatus == RequestStatus.IN_TRANSIT)
        {
            forecast.currentStatus = RequestStatus.SUCCESS

            Log.d("MAKO", "success")
            try{
                if(forecast.openWeatherRequestType == RequestType.CURRENT)
                {

                    ForecastRepository(app).insertWeatherForecast(QueryResponseToWeatherParser.parseCurrentJson(result))
                }else if(forecast.openWeatherRequestType == RequestType.FORECAST)
                {
                    ForecastRepository(app).insertWeatherForecasts(QueryResponseToWeatherParser.parseForecastJson(result))
                }
            }catch(jsonEx : JSONException)
            {
                forecast.currentStatus = RequestStatus.ERROR
                forecast.data = RESTMethod.ERROR_STRING
            }
        }

        // update entry in DB
        // for some reason ROOM doesn't want to update after a success so instead just insert a new row
        ForecastRepository(app).updateRequest(forecast)
    }
}
