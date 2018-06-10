package com.castro.andres.cnntakehome.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.castro.andres.cnntakehome.R
import com.castro.andres.cnntakehome.data.entities.WeatherForecast
import com.castro.andres.cnntakehome.ui.adapters.WeatherListAdapter


class MainFrag : Fragment()  {

    private val forecast = arrayListOf<WeatherForecast>()
    private val adapter = WeatherListAdapter()




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.weather_list_layout, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.weather_list)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(activity)



        return root
    }

}