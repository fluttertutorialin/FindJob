package com.alex.findjob.extensions

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

const val dataTimePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"

internal fun getLocalDateTime(date: String): LocalDateTime =
    SimpleDateFormat(dataTimePattern, Locale.getDefault()).parse(date)!!.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

internal fun Locale.checkEnLocale(): Locale =
    if (this.language.contains("en")) Locale.ENGLISH
    else this

internal fun Month.displayMonth(): String =
    getDisplayName(TextStyle.FULL, Locale.getDefault()).lowercase(Locale.getDefault())
