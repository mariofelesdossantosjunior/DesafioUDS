package com.mario.desafiouds.iu.perfil

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class PerfilViewModel(
    private val authentication: FirebaseAuth
) : ViewModel() {

    /**
     * Função responsavel por recuperar usuario logado
     */
    fun getUserLogged() = authentication.currentUser

    /**
     * Função responsavel pelo logaout
     */
    fun logoutAuth() = authentication.signOut()

}
