package com.example.shacklehotelbuddy

import java.text.SimpleDateFormat
import java.util.Date

fun Date.toReadableString(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(this)
}