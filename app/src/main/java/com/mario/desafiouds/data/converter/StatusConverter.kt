package com.mario.desafiouds.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mario.desafiouds.data.entity.StatusSchedule


class StatusConverter {

    @TypeConverter
    fun statusToJson(status: StatusSchedule?): String {
        return Gson().toJson(status)
    }

    @TypeConverter
    fun jsonToStatus(data: String?): StatusSchedule? {
        return Gson().fromJson(data, StatusSchedule::class.java)
    }
}
