package com.example.cpaweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cpaweb.databinding.ActivityFaqBinding
import com.example.cpaweb.databinding.ActivitySupportBinding

class Support : AppCompatActivity() {
    private lateinit var binding: ActivitySupportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener{
            finish()
        }
    }
}