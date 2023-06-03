package com.example.cpaweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cpaweb.databinding.ActivityAboutProfessionalBinding
import com.example.cpaweb.databinding.ActivityCommunityHomeBinding
import com.example.cpaweb.models.users.professionals.Professional

class AboutProfessional : AppCompatActivity() {
    private lateinit var binding: ActivityAboutProfessionalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutProfessionalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val professional = intent.getSerializableExtra("professionalData") as Professional
        binding.tvProfessionalNameText.text = professional.name.toString()
        binding.tvProfessionalSpecialityText.text = professional.especialidade.toString()
        binding.rbRating.rating = professional.ratings.map { it.rating }.average().toFloat()
        val about = professional.about?.toString()
        if(about != null){
            binding.tvProfessionalAboutText.text = about
        }else {
            binding.tvProfessionalAboutText.text = ""
        }
        binding.tvProfessionalEmailText.text = professional.email.toString()
        val address = professional.address?.street
        if(address != null){
            binding.tvProfessionalAddressText.text = address.toString()
        }else {
            binding.tvProfessionalAddressText.text = ""
        }

        binding.tvProfessionalPhoneText.text = professional.phone.toString()
    }
}