package com.anastasia.develop.learneasy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.anastasia.develop.learneasy.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.anastasia.develop.learneasy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavView.setupWithNavController(navController)


        val bottomSheetBehavior = BottomSheetBehavior.from(binding.ltBottomSheet.bottomSheet)

        binding.ltBottomSheet.btnSheetAddModule.setOnClickListener {
            navController.navigate(R.id.newModule)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        bottomSheetBehavior.peekHeight = 0
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.skipCollapsed = true

        bottomNavView.setOnItemSelectedListener {
            if (it.itemId == R.id.newModule) {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
                return@setOnItemSelectedListener true
            }
            return@setOnItemSelectedListener false
        }

    }
}