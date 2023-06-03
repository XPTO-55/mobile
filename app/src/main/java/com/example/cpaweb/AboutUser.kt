package com.example.cpaweb

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cpaweb.databinding.ActivityAboutUserBinding
import com.example.cpaweb.databinding.ActivityFaqBinding
import com.example.cpaweb.helpers.AuthManager

class AboutUser : AppCompatActivity() {
    private lateinit var binding: ActivityAboutUserBinding

    fun populateFields(){
        val user = AuthManager.getUser()
        binding.tvUsernameText.text = user.username?.toString()
        binding.tvPlaceCityText.text = String.format("%s, %s", "São Paulo", "SP")
        if(user.profileUrl != ""){
            binding.ciProfileImage.setImageURI(Uri.parse(user.profileUrl))
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        populateFields()

    }
}