package com.mario.desafiouds.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.StatusSchedule

@Dao
interface ScheduleDao {

    /**
     * Get a schedule by id.
     */
    @Query("SELECT * FROM schedule WHERE id = :id")
    fun getScheduleById(id: String): LiveData<Schedule>

    /**
     * Insert a schedule in the database. If the schedule already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchedule(schedule: Schedule): Long

    /**
     * Delete all schedule.
     */
    @Query("DELETE FROM schedule")
    fun deleteAllSchedule()

    /**
     * Find all schedules from status
     */
    @Query("SELECT * FROM schedule WHERE status = :status")
    fun findAllScheduleByStatus(status: StatusSchedule): LiveData<List<Schedule>>
}