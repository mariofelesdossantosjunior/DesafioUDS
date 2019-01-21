package com.mario.desafiouds.iu.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mario.desafiouds.data.repository.DataRepositoryFactory


class LoginViewModel(
    private val repository: DataRepositoryFactory,
    private val authentication: FirebaseAuth
) : ViewModel() {

    /**
     * Função responsavel pelo login
     */
    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            onResult(it.isComplete && it.isSuccessful)
        }
    }

    /**
     * Função responsavel pela redefinição de senha
     */
    fun redefinePassword(email: String, onResult: (Boolean) -> Unit) {
        authentication.sendPasswordResetEmail(email).addOnCompleteListener {
            onResult(it.isComplete && it.isSuccessful)
        }
    }

    /**
     * Função responsavel por recuperar usuario logado
     */
    fun getUserAuth(): FirebaseUser? {
        return authentication.currentUser
    }
}