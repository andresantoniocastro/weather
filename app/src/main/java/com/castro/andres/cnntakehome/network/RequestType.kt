package com.castro.andres.cnntakehome.network

enum class RequestType(val type : Int){
    UNKNOWN(0),
    CURRENT(1),
    FORECAST(2)
}