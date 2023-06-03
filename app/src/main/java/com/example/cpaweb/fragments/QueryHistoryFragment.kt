package com.example.cpaweb.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.CommunityHome
import com.example.cpaweb.R
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
        val homeActivity = activity as CommunityHome
        val toolBar = homeActivity.findViewById<Toolbar>(R.id.tb_toolbar)
        toolBar.title = String.format("CPA | %s", getString(R.string.menu_appointment))

        appointmentList = ArrayList()
        adapter = AppointmentListAdapter(this, appointmentList) { message ->
            Toast.makeText(
                view.context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
        appointmentRecyclerView = binding.rvContainerAppointment
        appointmentRecyclerView.adapter = adapter
        appointmentRecyclerView.layoutManager = LinearLayoutManager(this.context);
//        populateList()

        val service = Api.createService<AppointmentService>(AppointmentService::class.java)

        val userId = AuthManager.getUserInfoId()

        if(userId != null){
            service.getAppointments(userId).enqueue(
                GetAppointmentsCallback(
                    { message ->
                        Toast.makeText(
                            view.context,
                            message,
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    adapter
            ))
        }
    }
}