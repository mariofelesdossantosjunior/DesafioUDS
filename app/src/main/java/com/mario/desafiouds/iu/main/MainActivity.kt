package com.mario.desafiouds.iu.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mario.desafiouds.R
import com.mario.desafiouds.data.entity.StatusSchedule
import com.mario.desafiouds.iu.perfil.PerfilFragment
import com.mario.desafiouds.iu.schedules.ScheduleFragment
import com.mario.desafiouds.util.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(this)

        replaceFragment(ScheduleFragment()
            .apply {
                status = StatusSchedule.ABERTO
            }, R.id.content
        )
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {

            R.id.navigation_schedule_open -> {
                replaceFragment(ScheduleFragment().apply {
                    status = StatusSchedule.ABERTO
                }, R.id.content)
                return true
            }

            R.id.navigation_schedule_close -> {
                replaceFragment(ScheduleFragment().apply {
                    StatusSchedule.FECHADO
                }, R.id.content)
                return true
            }

            R.id.navigation_perfil -> {
                replaceFragment(PerfilFragment(), R.id.content)
                return true
            }
        }

        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        replaceFragment(ScheduleFragment(), R.id.content)
    }
}
