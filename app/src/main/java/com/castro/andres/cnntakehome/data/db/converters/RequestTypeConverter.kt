package com.castro.andres.cnntakehome.data.db.converters

import android.arch.persistence.room.TypeConverter
import com.castro.andres.cnntakehome.network.RequestType

class RequestTypeConverter {

    @TypeConverter
    fun intToRequestType(value : Int) : RequestType
    {
        return when(value)
        {
            1 -> RequestType.CURRENT
            2 -> RequestType.FORECAST
            else -> RequestType.UNKNOWN
        }
    }


    @TypeConverter
    fun requestTypeToInt(value : RequestType) : Int
    {
        return value.ordinal
    }


}