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



        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            // Iterate over all menu items
            for (i in 0 until binding.bottomNav.menu.size()) {
                val item = binding.bottomNav.menu.getItem(i)
                // Set icon based on selected or unselected state
                if (item.itemId == menuItem.itemId) {
                    // Selected item
                    item.setIcon(getSelectedIconRes(item.itemId))
                } else {
                    // Unselected items
                    item.setIcon(getUnselectedIconRes(item.itemId))
                }
            }

            when (menuItem.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.cities -> {
                    loadFragment(CitySelectionFragment())
                    true
                }
                else -> {
                    loadFragment(CitySelectionFragment())
                    true
                }
            }
        }
    }

    private fun getSelectedIconRes(itemId: Int): Int {
        return when (itemId) {
            R.id.home -> R.drawable.cloud_selected
            R.id.cities -> R.drawable.nav_selected
            else -> R.drawable.cloud_unselected
        }
    }

    private fun getUnselectedIconRes(itemId: Int): Int {
        return when (itemId) {
            R.id.home -> R.drawable.cloud_unselected
            R.id.cities -> R.drawable.nav_unselected
            else -> R.drawable.nav_unselected
        }
    }
    private fun loadFragment(fragment : Fragment) {
        val city = fragment.arguments?.getString("cityName", "London") ?: 0
        val bundle = Bundle().apply {
            putString("cityName", city.toString())
        }
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView2, fragment)
        transaction.commit()
    }

}
