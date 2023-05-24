package com.example.cpaweb.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.cpaweb.Login
import com.example.cpaweb.R
import com.example.cpaweb.databinding.FragmentPlacesBinding
import com.example.cpaweb.databinding.FragmentProfileBinding
import com.example.cpaweb.helpers.AuthManager

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AuthManager.init(view.context)

        binding.btnExit.setOnClickListener {
            AuthManager.clearAuthToken()
            startActivity(Intent(this.context, Login::class.java))
        }
    }
}