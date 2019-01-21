package com.mario.desafiouds.data.repository

import androidx.lifecycle.LiveData
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.StatusSchedule

class RemoteDataRepository : DataRepository {

    override fun findAllScheduleByStatus(status: StatusSchedule): LiveData<List<Schedule>> {
        throw UnsupportedOperationException("Operação não suportada")
    }

    override fun addSchedule(schedule: Schedule): Long {
        throw UnsupportedOperationException("Operação não suportada")
    }

    override fun getPasswordByEmail(email: String): String {
        throw UnsupportedOperationException("Operação não suportada")
    }

    override fun saveUser(name: String, email: String, password: String): Long {
        throw UnsupportedOperationException("Operação não suportada")
    }

}