package com.example.cpaweb.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.cpaweb.CommunityHome
import com.example.cpaweb.Faq
import com.example.cpaweb.R
import com.example.cpaweb.SITE_URL
import com.example.cpaweb.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeActivity = activity as CommunityHome
        val bottomNav = homeActivity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val toolBar = homeActivity.findViewById<Toolbar>(R.id.tb_toolbar)
        toolBar.title = String.format("CPA | %s", getString(R.string.menu_home))

        binding.homeBlog.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SITE_URL))
            startActivity(intent)
        }
        binding.homePlaces.setOnClickListener{
            bottomNav.selectedItemId = R.id.places
        }
        binding.homeSite.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SITE_URL))
            startActivity(intent)
        }
        binding.homeFaq.setOnClickListener{
            val intent = Intent(context, Faq::class.java)
            startActivity(intent)
        }
    }
}