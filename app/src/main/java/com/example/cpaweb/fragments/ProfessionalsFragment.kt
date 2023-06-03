package com.example.cpaweb.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.CommunityHome
import com.example.cpaweb.R
import com.example.cpaweb.adapters.ProfessionalListAdapter
import com.example.cpaweb.callbacks.GetProfessionalCallback
import com.example.cpaweb.databinding.FragmentProfessionalsBinding
import com.example.cpaweb.models.users.professionals.Professional
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.ProfessionalService
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate
class ProfessionalsFragment : Fragment(R.layout.fragment_professionals) {
    private lateinit var professionalRecyclerView: RecyclerView;
    private lateinit var professionalList: ArrayList<Professional>;
    private lateinit var adapter: ProfessionalListAdapter;
    private lateinit var binding: FragmentProfessionalsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfessionalsBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeActivity = activity as CommunityHome
        val toolBar = homeActivity.findViewById<Toolbar>(R.id.tb_toolbar)
        toolBar.title = String.format("CPA | %s", getString(R.string.menu_professionals))

        professionalList = ArrayList()
        adapter = ProfessionalListAdapter(this, professionalList)
        professionalRecyclerView = binding.rvProfessionalsList
        professionalRecyclerView.adapter = adapter
        professionalRecyclerView.setLayoutManager(LinearLayoutManager(this.context));
//        populateList()

        val service = Api.createService<ProfessionalService>(ProfessionalService::class.java)

        service.getProfessionals().enqueue(GetProfessionalCallback(
            { mensagem ->
                Toast.makeText(
                    view.context,
                    mensagem,
                    Toast.LENGTH_LONG
                ).show()
            },
            adapter
        ))


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun populateList() {
        val ap1 = Professional(
            0L,
            "Filena",
            "filena@gmail.com",
            "",
            "",
            "",
            "",
            LocalDate.now(),
            "",
            "",
            "Nutri",
            ArrayList(),
            null
        )
        val ap2 = Professional(
            1L,
            "Tatiana",
            "tatiana@gmail.com",
            "",
            "",
            "",
            "",
            LocalDate.now(),
            "",
            "",
            "Dentista",
            ArrayList(),
            null
        )
        professionalList.add(ap1)
        professionalList.add(ap2)
    }
}

