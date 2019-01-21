package com.mario.desafiouds.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mario.desafiouds.data.converter.StatusConverter
import com.mario.desafiouds.data.dao.ScheduleDao
import com.mario.desafiouds.data.dao.UserDao
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.User

@Database(
    entities = [User::class, Schedule::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(StatusConverter::class)

abstract class DataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun scheduleDao(): ScheduleDao
}