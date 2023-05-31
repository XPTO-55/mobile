package com.example.cpaweb.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.adapters.AppointmentListAdapter
import com.example.cpaweb.callbacks.GetAppointmentsCallback
import com.example.cpaweb.databinding.FragmentQueryHistoryBinding
import com.example.cpaweb.helpers.AuthManager
import com.example.cpaweb.models.appointments.Appointment
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.AppointmentService

class QueryHistoryFragment : Fragment() {
    private lateinit var appointmentRecyclerView: RecyclerView;
    private lateinit var appointmentList: ArrayList<Appointment>;
    private lateinit var adapter: AppointmentListAdapter;
    private lateinit var binding: FragmentQueryHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQueryHistoryBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appointmentList = ArrayList()
        adapter = AppointmentListAdapter(this, appointmentList)
        appointmentRecyclerView = binding.rvContainerAppointment
        appointmentRecyclerView.adapter = adapter
        appointmentRecyclerView.setLayoutManager(LinearLayoutManager(this.context));
//        populateList()

        val service = Api.createService<AppointmentService>(AppointmentService::class.java)

        val userId = AuthManager.getUserInfoId()

        print("userId: $userId")

        if(userId != null){
            service.getAppointments(userId).enqueue(
                GetAppointmentsCallback(
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
    }
}