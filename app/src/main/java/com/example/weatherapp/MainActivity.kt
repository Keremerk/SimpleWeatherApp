package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.CitySelectionFragment
import com.example.weatherapp.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    it.setIcon(R.drawable.cloud_selected)
                    true
                }
                R.id.cities -> {
                    loadFragment(CitySelectionFragment())
                    it.setIcon(R.drawable.nav_selected)
                    true
                }
                else -> {
                    loadFragment(CitySelectionFragment())
                    it.setIcon(R.drawable.cloud_selected)

                    true
                }
            }
        }
    }

    private fun loadFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

}
