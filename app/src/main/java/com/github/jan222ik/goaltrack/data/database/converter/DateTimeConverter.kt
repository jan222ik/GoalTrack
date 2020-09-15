package com.github.jan222ik.goaltrack.data.database.converter

import androidx.room.TypeConverter
import java.time.*

class DateTimeConverter {
    @TypeConverter
    fun fromTimeDatestamp(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneOffset.UTC) }
    }

    @TypeConverter
    fun dateTimeToTimestamp(date: LocalDateTime?): Long? {
        return date?.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalTime? {
        return value?.let { LocalTime.ofNanoOfDay(it) }
    }

    @TypeConverter
    fun timeToTimestamp(time: LocalTime?): Long? {
        return time?.toNanoOfDay()
    }

    @TypeConverter
    fun fromDatestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun dateToDatestamp(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}