package com.castro.andres.cnntakehome.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.castro.andres.cnntakehome.R
import com.castro.andres.cnntakehome.data.viewmodels.ForecastViewModel
import com.castro.andres.cnntakehome.ui.adapters.WeatherListAdapter


class MainFrag : Fragment()  {

    private var adapter : WeatherListAdapter? = null

    private lateinit var viewModel : ForecastViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ForecastViewModel::class.java)

        adapter = WeatherListAdapter(activity!!.supportFragmentManager)
        subscribe(viewModel)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.weather_list_layout, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.weather_list)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(activity)

        return root
    }

    private fun subscribe(model : ForecastViewModel)
    {
        model.allForecasts.observe(this, Observer {
            if(adapter != null)
            {
                if (it != null) {
                    adapter!!.update(it)
                }
            }
        })
    }

}