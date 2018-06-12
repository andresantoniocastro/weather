package com.castro.andres.cnntakehome.data.db

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context
import com.castro.andres.cnntakehome.data.db.contracts.RestDAO
import com.castro.andres.cnntakehome.data.db.converters.RequestStatusConverter
import com.castro.andres.cnntakehome.data.db.converters.RequestTypeConverter
import com.castro.andres.cnntakehome.data.entities.ForecastQuery
import com.castro.andres.cnntakehome.data.entities.WeatherForecast

@Database(entities = [(ForecastQuery::class), (WeatherForecast::class)], version = 1)
@TypeConverters(*arrayOf(RequestTypeConverter::class, RequestStatusConverter::class))
abstract class ForecastDatabase : RoomDatabase(){

    abstract fun getRestDao() : RestDAO

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    companion object {
        private var instance : ForecastDatabase? = null

        fun getInstance(context : Context )  : ForecastDatabase? {
            if(instance == null)
            {
                synchronized(ForecastDatabase::class)
                {
                    instance = Room.databaseBuilder(context.applicationContext,
                                                    ForecastDatabase::class.java, "forecasting.db").build()
                }
            }

            return instance
        }


    }
}