package com.example.prueba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation = findViewById(R.id.bottom_navigation)

        // Configurar navegación
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_canchas -> {
                    loadFragment(CanchasFragment(), R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                R.id.nav_reservas -> {
                    loadFragment(ReservasFragment(), R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                R.id.nav_pagos -> {
                    loadFragment(PagosFragment(), R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                else -> false
            }
        }

        // Cargar fragmento inicial
        loadFragment(CanchasFragment(), 0, 0)
    }

    private fun loadFragment(fragment: Fragment, enter: Int, exit: Int) {
        supportFragmentManager.beginTransaction().apply {
            if(enter != 0 && exit != 0) setCustomAnimations(enter, exit)
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}