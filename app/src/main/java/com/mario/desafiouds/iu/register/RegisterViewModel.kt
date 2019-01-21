package com.mario.desafiouds.iu.register

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterViewModel(
    private val authentication: FirebaseAuth
) : ViewModel() {

    /**
     * Função responsavel por registrar um novo usuario no Firebase
     */
    fun register(email: String, password: String, userName: String, onResult: (Boolean, Exception?) -> Unit) {
        authentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isComplete && it.isSuccessful) {
                authentication.currentUser?.updateProfile(
                    UserProfileChangeRequest
                        .Builder()
                        .setDisplayName(userName)
                        .build()
                )

                onResult(true, it.exception)
            } else {
                onResult(false, it.exception)
            }
        }
    }
}