/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/*
 *  SOME of the code comes from the sample on googles github. You can find it at https://github.com/googlesamples/android-NetworkConnect/blob/master/Application/src/main/java/com/example/android/networkconnect/NetworkFragment.java
 *  I'm providing the notice because I am
 *  pretty sure I need to (and its a nice thing to do). Will make note of what comes from the sample.
 *  It should be pretty clear regardless.
 */

package com.castro.andres.cnntakehome.network

import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import com.castro.andres.cnntakehome.data.db.ForecastRepository
import com.castro.andres.cnntakehome.data.entities.ForecastQuery
import com.castro.andres.cnntakehome.data.viewmodels.LatestQueryViewModel
import java.util.*
import kotlin.concurrent.schedule


/**
    Headless Fragment that is in charge of executing network traffic.

    This class is responsible for making sure that a) we are on the right network and b) doing network stuff off the main thread.

*/
class NetworkFragment : Fragment() {

    private lateinit var viewModel : LatestQueryViewModel
    private var timer : Timer? = null

    companion object {

        private const val TAG = "NetworkFragment"

        /**
         * Static initializer for NetworkFragment that sets the URL of the host it will be downloading
         * from. This is definitely from the  github sample. We don't need the URL_KEY from the example
         * so get rid of it.
         */
        fun getInstance(fragmentManager: FragmentManager): NetworkFragment {
            // Recover NetworkFragment in case we are re-creating the Activity due to a config change.
            // This is necessary because NetworkFragment might have a task that began running before
            // the config change and has not finished yet.
            // The NetworkFragment is recoverable via this method because it calls
            // setRetainInstance(true) upon creation.
            var networkFragment = fragmentManager.findFragmentByTag(NetworkFragment.TAG)
            if (networkFragment == null) {
                networkFragment = NetworkFragment()
//                val args = Bundle()
//                networkFragment.arguments = args
                fragmentManager.beginTransaction().add(networkFragment, TAG).commit()
            }
            return networkFragment as NetworkFragment
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(LatestQueryViewModel::class.java)


        subscribeToLatestQuery(viewModel)

    }

    private fun makeOpenWeatherApiCalls() {

        // make ssure we are connected to the internet
        if (isConnectedToNetwork()) {
            if (activity?.application != null) {

                var forecast = RESTMethod.constructQuery(RequestType.CURRENT)
                ForecastRepository(activity!!.application).insertNewRequest(forecast)

                RESTThread(activity!!.application, forecast).execute()
            }
        }

        //TODO: display to user that we werent connected if this block fails
    }

    private fun isConnectedToNetwork() : Boolean
    {
        if(activity == null || activity?.applicationContext == null)
            return false

        val cm = activity?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }


    private fun subscribeToLatestQuery(viewModel: LatestQueryViewModel) {
        viewModel?.latestQuery.observe(this, Observer { forecast ->
            if(forecast != null)
            {
                // check to see if it has been at least 10 mins since last request
                val timeToWait = forecast.attemptTime + 1000 * 60 * 10 // 10 mins after latest
                val currentTime = System.currentTimeMillis()

                val timeLeft = timeToWait - currentTime


                if(timeLeft > 0 )
                {
                    stopTimer()
                    startTimer(timeLeft)
                }else
                {
                    makeOpenWeatherApiCalls()
                }
            }else
            {
                Log.d("MAKO", "pretty sure there is nothing in the db so just go ahead and start up the net stuff")
                makeOpenWeatherApiCalls()
            }

        })

    }

    private fun startTimer(timeDelay : Long)
    {
        timer = Timer()

        timer!!.schedule(timeDelay) { makeOpenWeatherApiCalls()}
    }

    private fun stopTimer()
    {
        if(timer != null)
        {
            timer!!.cancel()
            timer = null
        }
    }

}

private class RESTThread(private val app: Application, private val forecast: ForecastQuery) : AsyncTask<ForecastQuery, Void, String>(){


    override fun onPreExecute() {
        super.onPreExecute()

        // update current query to say that we are talking to the server
        forecast.currentStatus = RequestStatus.IN_TRANSIT
        ForecastRepository(app).updateRequest(forecast)
    }

    override fun doInBackground(vararg params: ForecastQuery?): String {

        // do the actual guts of talking to the server
        var result = ""
        try{
            result = RESTMethod.get(forecast.requestText)

        // if it wasn't successful then update the db as having an issue
        }catch (re : RuntimeException)
        {
            forecast.currentStatus = RequestStatus.ERROR
            result = re.message ?: RESTMethod.ERROR_STRING
        }

        return result

    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        // if something went wrong other than the bad response code deal with it ow
        if(result == RESTMethod.ERROR_STRING)
        {
            forecast.currentStatus == RequestStatus.ERROR
        // if there was no error than go ahead and update the status to be a success
        }else if(forecast.currentStatus == RequestStatus.IN_TRANSIT)
        {
            forecast.currentStatus = RequestStatus.SUCCESS
            forecast.data = result ?: ""
        }

        // update entry in DB
        ForecastRepository(app).insertNewRequest(forecast)
    }
}