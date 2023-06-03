package com.example.cpaweb.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransitionImpl
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.R
import com.example.cpaweb.fragments.FragmentAvaliacaoDialog
import com.example.cpaweb.fragments.QueryHistoryFragment
import com.example.cpaweb.models.appointments.Appointment
import com.example.cpaweb.models.users.CreateRatingRequest
import com.example.cpaweb.rest.Api
import com.example.cpaweb.services.RatingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
class AppointmentListAdapter(
    val context: QueryHistoryFragment,
    var appointmentList: ArrayList<Appointment>,
    private val handleMessage: (String) -> Unit
    ):
    RecyclerView.Adapter<AppointmentListAdapter.AppointmentItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentListAdapter.AppointmentItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_query_historic, parent, false)
        return AppointmentListAdapter.AppointmentItemViewHolder(view)
    }

    @SuppressLint("RestrictedApi")
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
        holder.btnRating.setOnClickListener {
            val context = holder.itemView.context
            val dialog = FragmentAvaliacaoDialog(currentAppointment, ::enviarFeedback)
            dialog.show((context as AppCompatActivity).supportFragmentManager, "DIALOG_AVALIACAO")
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
        val btnRating = itemView.findViewById<Button>(R.id.btn_rating_professional)
    }

    private fun enviarFeedback(professionalId: Long, comment: String, rating: Double) {
        // Pegar o avaliacao e enviar para o back-end
        val service = Api.createService(RatingService::class.java)
        val createRatingRequest = CreateRatingRequest(comment, rating)
        val request: Call<Void> = service.createProfessionalRating(professionalId, createRatingRequest)

        request.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    handleMessage("Obrigado pelo seu feedback!")
                }else if(response.code() >= 400){
                    handleMessage("Tivemos um problema com seu feedback, poderia enviar novamente?")
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                handleMessage("Ops! houve um erro na conexão")
            }
        })
    }
}