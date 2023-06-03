package com.example.cpaweb.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import com.example.cpaweb.CommunityHome
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
        val homeActivity = activity as CommunityHome
        val toolBar = homeActivity.findViewById<Toolbar>(R.id.tb_toolbar)
        toolBar.title = String.format("CPA | %s", getString(R.string.menu_profile))

        binding.btnExit.setOnClickListener {
            AuthManager.clearAuthToken()
            startActivity(Intent(this.context, Login::class.java))
        }
    }
}