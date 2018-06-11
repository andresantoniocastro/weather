package com.castro.andres.cnntakehome.network

enum class RequestStatus(val status : Int) {
    NO_CONNECTION(0), // Never even sent
    ERROR(1), // Something went wrong on the way
    IN_TRANSIT(2), // Currently in the middle of talking to the server
    SUCCESS(3) // Query is done
}