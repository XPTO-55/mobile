package com.example.cpaweb.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.cpaweb.AboutUser
import com.example.cpaweb.CommunityHome
import com.example.cpaweb.EditUser
import com.example.cpaweb.Faq
import com.example.cpaweb.Login
import com.example.cpaweb.R
import com.example.cpaweb.SITE_URL
import com.example.cpaweb.Support
import com.example.cpaweb.databinding.FragmentSettingsBinding
import com.example.cpaweb.helpers.AuthManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeActivity = activity as CommunityHome
        val bottomNav = homeActivity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val toolBar = homeActivity.findViewById<Toolbar>(R.id.tb_toolbar)
        toolBar.title = String.format("CPA | %s", getString(R.string.menu_settings))

        binding.containerProfile.setOnClickListener{
            startActivity(Intent(context, AboutUser::class.java))
        }

        binding.settingsEditProfile.setOnClickListener{
            startActivity(Intent(context, EditUser::class.java))
        }

        binding.settingsAppointmentHistory.setOnClickListener{
            bottomNav.selectedItemId = R.id.appointment
        }

        binding.settingsBlog.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SITE_URL))
            startActivity(intent)
        }

        binding.settingsFaq.setOnClickListener{
            startActivity(Intent(context, Faq::class.java))
        }

        binding.settingsSupport.setOnClickListener{
            startActivity(Intent(context, Support::class.java))
        }

        binding.containerExit.setOnClickListener{
            AuthManager.clearAuthToken()
            startActivity(Intent(context, Login::class.java))
        }
    }
}