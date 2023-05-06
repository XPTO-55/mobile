package com.example.cpaweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cpaweb.databinding.ActivityCommunityHomeBinding
import com.example.cpaweb.fragments.*

class CommunityHome : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeMenuOption(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> changeMenuOption(HomeFragment())
                R.id.places -> changeMenuOption(PlacesFragment())
                R.id.professionals -> changeMenuOption(ProfessionalsFragment())
//                R.id.blog -> changeMenuOption(BlogFragment())
                R.id.profile -> changeMenuOption(ProfileFragment())
                R.id.appointment -> changeMenuOption(ProfileFragment())

                else -> {
                }

            }
            true
        }

        val prefs = getSharedPreferences("AUTH", MODE_PRIVATE)
        val token = prefs.getString("token", null)

        if (token == null) {
            println("SEM TOKEN")
        } else {
            println(token)
        }
    }

    private fun changeMenuOption(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}