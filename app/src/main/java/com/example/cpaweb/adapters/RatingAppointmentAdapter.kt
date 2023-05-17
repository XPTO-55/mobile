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
import com.example.cpaweb.models.appointments.Appointment
import java.time.LocalDateTime

class RatingAppointmentAdapter (val context: Context, var appointmentList: ArrayList<Appointment>):
    RecyclerView.Adapter<RatingAppointmentAdapter.AppointmentItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RatingAppointmentAdapter.AppointmentItemViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.appointment_data_view_row, parent, false)
        return RatingAppointmentAdapter.AppointmentItemViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: RatingAppointmentAdapter.AppointmentItemViewHolder,
        position: Int
    ) {
        val currentAppointment = appointmentList[position]
        holder.txtPatient.text = currentAppointment.patient.toString()
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

    class AppointmentItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtPatient = itemView.findViewById<TextView>(R.id.tv_appointment_patient_name_text)
        val txtDate = itemView.findViewById<TextView>(R.id.tv_appointment_data_text)
        val txtStatus = itemView.findViewById<TextView>(R.id.tv_appointment_status_text)
    }
}