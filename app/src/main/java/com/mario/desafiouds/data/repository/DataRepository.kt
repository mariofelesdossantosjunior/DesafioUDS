package com.mario.desafiouds.data.repository

import androidx.lifecycle.LiveData
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.StatusSchedule

interface DataRepository {

    fun saveUser(name: String, email: String, password: String): Long

    fun getPasswordByEmail(email: String): String?

    fun addSchedule(schedule: Schedule): Long

    fun findAllScheduleByStatus(status: StatusSchedule): LiveData<List<Schedule>>
}