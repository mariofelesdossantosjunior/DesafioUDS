package com.mario.desafiouds.iu.add

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mario.desafiouds.data.entity.Schedule
import com.mario.desafiouds.data.entity.StatusSchedule
import com.mario.desafiouds.data.repository.DataRepositoryFactory

class AddScheduleViewModel(
    private val repository: DataRepositoryFactory,
    private val authentication: FirebaseAuth
) : ViewModel() {

    /**
     * Função responsavel por gerar uma nova pauta
     * e salva-la na database
     */
    fun addSchedule(
        title: String,
        description: String,
        detail: String,
        author: String,
        onResult: (Boolean) -> Unit) {
        val addSchedule = repository.retrieveLocalSource().addSchedule(
            Schedule(
                title = title,
                description = description,
                detail = detail,
                author = author,
                status = StatusSchedule.ABERTO
            )
        )
        if (addSchedule > 0) {
            onResult(true)
        } else {
            onResult(false)
        }
    }

    /**
     * Função responsavel por recuperar o nome do usuario logado
     */
    fun getUserLoggin() = authentication.currentUser?.displayName
}
