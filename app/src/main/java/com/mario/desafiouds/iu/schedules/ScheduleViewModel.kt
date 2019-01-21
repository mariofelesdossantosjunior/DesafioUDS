package com.mario.desafiouds.iu.schedules

import androidx.lifecycle.ViewModel
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.StatusSchedule
import com.mario.desafiouds.data.repository.DataRepositoryFactory

class ScheduleViewModel(
    val repository: DataRepositoryFactory
) : ViewModel() {

    /**
     * Função responsavel por recuperar as pautas por status na database
     */
    fun getAllSchedulesByStatus(status: StatusSchedule) =
        repository.retrieveLocalSource().findAllScheduleByStatus(status)

    /**
     * Função responsavel por mudar o status da pauta na database
     */
    fun updateSchedule(schedule: Schedule) =
        repository.retrieveLocalSource().addSchedule(schedule)
}
