package com.example.cpaweb.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.R
import com.example.cpaweb.adapters.ProfessionalListAdapter
import com.example.cpaweb.databinding.AppointmentDataViewRowBinding
import com.example.cpaweb.databinding.FragmentProfessionalsBinding
import com.example.cpaweb.databinding.ResFragAvaliacaoDialogBinding
import com.example.cpaweb.models.appointments.Appointment
import com.example.cpaweb.models.users.professionals.Professional
import java.time.LocalDate
import java.time.LocalDateTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfessionalsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        professionalList = ArrayList()
        adapter = ProfessionalListAdapter(this, professionalList)
        professionalRecyclerView = binding.rvProfessionalsList
        professionalRecyclerView.adapter = adapter
        professionalRecyclerView.setLayoutManager(LinearLayoutManager(this.context));
        populateList()

        adapter.notifyDataSetChanged();
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
            "Nutri"
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
            "Dentista"
        )
        professionalList.add(ap1)
        professionalList.add(ap2)
    }
}

