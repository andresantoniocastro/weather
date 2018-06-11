package com.castro.andres.cnntakehome.data.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.castro.andres.cnntakehome.data.db.ForecastRepository

class LatestQueryViewModel(app : Application) : AndroidViewModel(app) {
    private val repo  = ForecastRepository(app)
    val latestQuery = repo.latestForecastQuery
}