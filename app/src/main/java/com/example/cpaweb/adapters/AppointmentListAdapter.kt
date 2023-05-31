package com.example.cpaweb.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.R
import com.example.cpaweb.fragments.QueryHistoryFragment
import com.example.cpaweb.models.appointments.Appointment
import java.time.LocalDateTime

class AppointmentListAdapter(val context: QueryHistoryFragment, var appointmentList: ArrayList<Appointment>):
    RecyclerView.Adapter<AppointmentListAdapter.AppointmentItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentListAdapter.AppointmentItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_query_historic, parent, false)
        return AppointmentListAdapter.AppointmentItemViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: AppointmentListAdapter.AppointmentItemViewHolder,
        position: Int
    ) {
        val currentAppointment = appointmentList[position]
        holder.txtPatient.text = currentAppointment.patient.toString()
        holder.txtProfessional.text = currentAppointment.professional.toString()
        holder.txtDate.text = currentAppointment.date.toString()
        if(currentAppointment.date.isAfter(LocalDateTime.now()) == true){
            holder.txtStatus.text = "Finalizada"
        }else{
            holder.txtStatus.text = "Agendada"
        }
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }

    fun updateData(newItems: List<Appointment>) {
        appointmentList.clear()
        appointmentList.addAll(newItems)
        notifyDataSetChanged()
    }

    class AppointmentItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtProfessional = itemView.findViewById<TextView>(R.id.tv_appointment_professional_text_row)
        val txtPatient = itemView.findViewById<TextView>(R.id.tv_appointment_professional_text_row)
        val txtDate = itemView.findViewById<TextView>(R.id.tv_appointment_data_text_row)
        val txtStatus = itemView.findViewById<TextView>(R.id.tv_appointment_status_text_row)
    }
}