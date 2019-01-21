package com.mario.desafiouds.data.repository

import com.mario.desafiouds.data.dao.ScheduleDao
import com.mario.desafiouds.data.dao.UserDao
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.StatusSchedule
import com.mario.desafiouds.data.entity.User

class LocalDataRepository(
    private val userDao: UserDao,
    private val scheduleDao: ScheduleDao
) : DataRepository {

    override fun findAllScheduleByStatus(status: StatusSchedule) =
        scheduleDao.findAllScheduleByStatus(status)

    override fun addSchedule(schedule: Schedule) =
        scheduleDao.insertSchedule(schedule)

    override fun getPasswordByEmail(email: String) =
        userDao.getPasswordByEmail(email)

    override fun saveUser(name: String, email: String, password: String) =
        userDao.insertUser(
            User(
                name = name,
                email = email,
                password = password
            )
        )


}

