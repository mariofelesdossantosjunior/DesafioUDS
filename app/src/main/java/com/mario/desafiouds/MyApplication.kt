package com.mario.desafiouds

import android.app.Application
import com.google.firebase.FirebaseApp
import com.mario.desafiouds.di.applicationModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        startKoin(this, listOf(applicationModule))
    }
}