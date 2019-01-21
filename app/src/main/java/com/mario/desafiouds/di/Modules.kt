package com.mario.desafiouds.di

import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.mario.desafiouds.data.DataBase
import com.mario.desafiouds.data.repository.DataRepository
import com.mario.desafiouds.data.repository.DataRepositoryFactory
import com.mario.desafiouds.data.repository.LocalDataRepository
import com.mario.desafiouds.data.repository.RemoteDataRepository
import com.mario.desafiouds.iu.add.AddScheduleViewModel
import com.mario.desafiouds.iu.login.LoginViewModel
import com.mario.desafiouds.iu.main.MainViewModel
import com.mario.desafiouds.iu.perfil.PerfilViewModel
import com.mario.desafiouds.iu.register.RegisterViewModel
import com.mario.desafiouds.iu.schedules.ScheduleViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {

    single(
        definition = {
            Room.databaseBuilder(
                androidApplication(),
                DataBase::class.java,
                "uds-database.db"
            )
                .allowMainThreadQueries()
                .build()
        })

    single(definition = { FirebaseAuth.getInstance() })

    single(definition = { get<DataBase>().userDao() })
    single(definition = { get<DataBase>().scheduleDao() })

    factory<DataRepository>("local") { LocalDataRepository(get(), get()) }
    factory<DataRepository>("remote") { RemoteDataRepository() }
    factory { DataRepositoryFactory(get("local"), get("remote")) }

    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { PerfilViewModel(get()) }
    viewModel { ScheduleViewModel(get()) }
    viewModel { AddScheduleViewModel(get(), get()) }

}