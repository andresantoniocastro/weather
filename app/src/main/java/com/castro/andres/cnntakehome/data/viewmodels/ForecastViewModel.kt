package com.castro.andres.cnntakehome.data.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.castro.andres.cnntakehome.data.db.ForecastRepository

class ForecastViewModel(app : Application) : AndroidViewModel(app) {

    private val repo  = ForecastRepository(app)
    val allForecasts = repo.restDao.getForecasts()
}