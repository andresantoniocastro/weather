package com.castro.andres.cnntakehome.data.db.converters

import android.arch.persistence.room.TypeConverter
import com.castro.andres.cnntakehome.network.RequestStatus

class RequestStatusConverter {

    @TypeConverter
    fun intToRequestStatus(value : Int) : RequestStatus
    {
        return when(value)
        {
            1 -> RequestStatus.ERROR
            2 -> RequestStatus.IN_TRANSIT
            3 -> RequestStatus.SUCCESS
            else -> RequestStatus.NO_CONNECTION
        }
    }

    @TypeConverter
    fun requestStatusToInt(value : RequestStatus) : Int
    {
        return value.ordinal
    }


}