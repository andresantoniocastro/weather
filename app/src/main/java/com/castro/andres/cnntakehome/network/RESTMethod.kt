package com.castro.andres.cnntakehome.network

import com.castro.andres.cnntakehome.data.entities.ForecastQuery
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * This class is responsible for handling the actual GET query
 */
class RESTMethod {

    companion object {

        const val ERROR_STRING = "Couldn't connect"
        private const val ATLANTA_CODE = 4180439

        //TODO: store this in a more secure way
        private const val OPEN_WEATHER_CODE = "ca298fdf97e0e2ea9d102f08b8dd378d"

        fun constructQuery(type : RequestType) : ForecastQuery
        {
            return ForecastQuery(System.currentTimeMillis(), type, RequestStatus.NO_CONNECTION, constructServerCall(type) )
        }

        private fun constructServerCall(type : RequestType) : String
        {
            return when(type)
            {
                RequestType.CURRENT -> "http://api.openweathermap.org/data/2.5/weather?id=$ATLANTA_CODE&appid=$OPEN_WEATHER_CODE"
                RequestType.FORECAST -> "http://api.openweathermap.org/data/2.5/forecast?id=$ATLANTA_CODE&appid=$OPEN_WEATHER_CODE&cnt=5"
                else -> ""
            }
        }

        /**
         * This method blocks
         */
        fun get(url: String): String {

            if (url == "")
                return ERROR_STRING

            val remoteURL = URL(url)
            val urlConnection = remoteURL.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.doOutput = false
            urlConnection.connectTimeout = 5000
            urlConnection.readTimeout = 5000
            urlConnection.connect()


            if (urlConnection.responseCode != 200) {
                throw RuntimeException("Failed : HTTP error code : "
                        + urlConnection.responseCode)
            }


            try {
                val input = BufferedInputStream(urlConnection.inputStream)
                val bufferedReader = BufferedReader(InputStreamReader(input))
                val builder = StringBuilder()
                var inputString = bufferedReader.readLine()
                while (inputString != null) {
                    builder.append(inputString)

                    inputString = bufferedReader.readLine()

                }

                return builder.toString()

            } finally {
                urlConnection.disconnect()
            }

            return ERROR_STRING
        }
    }

}

/*
private class GetTask() : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String {

        // the video says to use the apache client, but that was removed and HTTPURLConnection is now preffered
        val url = URL(params[0])
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.doOutput = false
        urlConnection.connectTimeout = 5000
        urlConnection.readTimeout = 5000
        urlConnection.connect()


        if (urlConnection.responseCode != 200) {
            throw RuntimeException("Failed : HTTP error code : "
                    + urlConnection.responseCode);
        }


        try {
            val input = BufferedInputStream(urlConnection.inputStream)
            val bufferedReader = BufferedReader(InputStreamReader(input))
            val builder = StringBuilder()
            var inputString = bufferedReader.readLine()
            while (inputString != null) {
                builder.append(inputString)

                inputString = bufferedReader.readLine()

            }

            return builder.toString()

        } finally {
            urlConnection.disconnect()
        }

        return "Couldn't connect"
    }


    override fun onPostExecute(result: String?) {
        Log.d("MAKO", "result of network stuff: $result")
    }

}
        */